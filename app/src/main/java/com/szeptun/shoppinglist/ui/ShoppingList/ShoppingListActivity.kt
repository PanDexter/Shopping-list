package com.szeptun.shoppinglist.ui.ShoppingList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.szeptun.shoppinglist.R
import com.szeptun.shoppinglist.databinding.ActivityShoppinglistBinding
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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
        intent.apply {
            shoppingList = getParcelableExtra(SHOPPING_LIST) as ShoppingList?
        }
        setupRecycler()
        observeDeleteClicks()
        onSaveButtonClick()
        onNameChange()
        onNewItemAdd()
    }

    private fun onSaveButtonClick() {
        binding.saveList.setOnClickListener {
            shoppingList?.let { list ->
                viewModel.saveList(list)
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
                val newList = it.itemsList + Product(binding.addProduct.productName.text.toString())
                shoppingList = it.copy(itemsList = newList)
                productsAdapter.setDataWithDiff(newList)
            }
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
            productsAdapter.setDataWithDiff(filteredList)
        }, {
            Log.e(ERROR_TAG, "Error while observing delete clicks")
        }).addTo(disposable)
    }

    companion object {
        const val SHOPPING_LIST = "shopping_list"
        private val ERROR_TAG = ShoppingListActivity::class.java.simpleName
    }

}

@BindingAdapter("viewVisibility")
fun ConstraintLayout.viewVisibility(listState: ListState?) {
    listState?.let {
        visibility = when (listState) {
            ListState.ACTIVE -> View.VISIBLE
            ListState.ARCHIVE -> View.GONE
        }
    }
}
