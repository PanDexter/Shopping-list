package com.szeptun.shoppinglist.ui.ShoppingList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.szeptun.shoppinglist.databinding.FragmentShoppinglistBinding
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ShoppingListFragment(shoppingList: ShoppingList) : Fragment() {

    @Inject
    lateinit var viewModel: ShoppingListViewModel

    private lateinit var binding: FragmentShoppinglistBinding
    private lateinit var productsAdapter: ProductsAdapter
    private val disposable = CompositeDisposable()
    private var adapterItems = shoppingList

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentShoppinglistBinding.inflate(inflater).apply {
            binding = this
            setupRecycler()
            observeDeleteClicks()
            binding.btnAdd.setOnClickListener {

            }
            //TODO SAVED STATE DO ZAPAMIETANIA ITEMOW, dodanie do bazki po zatwierdzeniu jakimś
        }.root


    private fun FragmentShoppinglistBinding.setupRecycler() {
        with(rvList) {
            layoutManager = LinearLayoutManager(context)
            adapter = productsAdapter
        }
    }

    private fun observeDeleteClicks() {
        productsAdapter.selectedItemStream.subscribe({
            val filteredList =
                productsAdapter.items.filterIndexed { index, _ -> index != it }
            productsAdapter.setDataWithDiff(filteredList)
        }, {
            Log.e(ERROR_TAG, "Error while observing delete clicks")
        }).addTo(disposable)
    }

    companion object {
        private val ERROR_TAG = ShoppingListFragment::class.java.simpleName
    }

}
