package com.szeptun.shoppinglist.ui.ShoppingList

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.szeptun.shoppinglist.R
import com.szeptun.shoppinglist.databinding.ActivityShoppinglistBinding
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import com.szeptun.shoppinglist.ui.ShoppingList.recycler.ProductsAdapter
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.joda.time.LocalDateTime
import javax.inject.Inject

class ShoppingListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: ShoppingListViewModel
    @Inject
    lateinit var productsAdapter: ProductsAdapter

    private lateinit var binding: ActivityShoppinglistBinding
    private val disposable = CompositeDisposable()
    private var shoppingList: ShoppingList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shoppinglist)
        shoppingList = intent.getParcelableExtra(SHOPPING_LIST) as ShoppingList? ?: ShoppingList(
            id = 0,
            name = "Name",
            listState = ListState.ACTIVE,
            date = LocalDateTime.now(),
            itemsList = emptyList()
        )

        binding.name.setText(shoppingList?.name)
        binding.listState = shoppingList?.listState
        setupRecycler()
        observeDeleteClicks()
        onSaveButtonClick()
        onNameChange()
        onNewItemAdd()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SHOPPING_LIST_STATE, shoppingList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        when (val shoppingList =
            savedInstanceState.getParcelable(SHOPPING_LIST_STATE) as ShoppingList?) {
            null -> return
            else -> {
                this.shoppingList = shoppingList
                productsAdapter.setDataWithDiff(shoppingList.itemsList)
            }
        }
    }

    private fun onSaveButtonClick() {
        binding.saveList.setOnClickListener {
            shoppingList?.let { list ->
                viewModel.saveList(list)
                finish()
            }
        }
    }

    private fun onNameChange() {
        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                shoppingList = shoppingList?.copy(name = sequence.toString())
            }
        })
    }

    private fun onNewItemAdd() {
        binding.addProduct.saveProduct.setOnClickListener {
            shoppingList?.let {
                val newList =
                    it.itemsList + Product(0, binding.addProduct.productName.text.toString())
                shoppingList = shoppingList?.copy(itemsList = newList)
                productsAdapter.setDataWithDiff(newList)
            }
            hideKeyboard()
            binding.addProduct.productName.text.clear()
        }
    }


    private fun setupRecycler() {
        with(binding.rvList) {
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
            shoppingList?.let {
                productsAdapter.items = it.itemsList
                productsAdapter.listState = it.listState
            }
        }
    }

    private fun observeDeleteClicks() {
        productsAdapter.selectedItemStream.subscribe({
            val filteredList =
                productsAdapter.items.filterIndexed { index, _ -> index != it }
            shoppingList = shoppingList?.copy(itemsList = filteredList)
            if (productsAdapter.items[it].id != 0) {
                viewModel.removeProduct(productsAdapter.items[it])
            }
            productsAdapter.setDataWithDiff(filteredList)
        }, {
            Log.e(ERROR_TAG, "Error while observing delete clicks")
        }).addTo(disposable)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    companion object {
        const val SHOPPING_LIST = "shopping_list"
        private const val SHOPPING_LIST_STATE = "shopping_list_state"
        private val ERROR_TAG = ShoppingListActivity::class.java.simpleName
    }

}