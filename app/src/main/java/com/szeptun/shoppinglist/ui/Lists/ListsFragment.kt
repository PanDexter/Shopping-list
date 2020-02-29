package com.szeptun.shoppinglist.ui.Lists

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szeptun.shoppinglist.databinding.FragmentListBinding
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.ShoppingList
import com.szeptun.shoppinglist.ui.ShoppingList.ShoppingListActivity
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ListsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: ListViewModel
    @Inject
    lateinit var listAdapter: ShoppingListAdapter

    private lateinit var binding: FragmentListBinding
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentListBinding.inflate(inflater).apply {
            binding = this
            binding.listState = getListStateFromBundle()
            setupRecycler()
            observeItemClicks()
            observeItems()
            onAddButtonClick()
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    private fun FragmentListBinding.setupRecycler() {
        with(rvList) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    fun getListStateFromBundle() =
        arguments?.get(LIST_STATE) as ListState


    private fun observeItems() {
        viewModel.shoppingListItemsStream
            .subscribe({
                listAdapter.setDataWithDiff(it)
            }, {
                Log.e(ERROR_TAG, "Error while observing adapter items")
            }).addTo(disposable)
    }

    private fun observeItemClicks() {
        listAdapter.selectedItemStream
            .subscribe({
                val selectedItemShoppingList = listAdapter.items[it]
                openShoppingListActivity(selectedItemShoppingList)
            }, {
                Log.e(ERROR_TAG, "Error while observing RV clicks")
            })
            .addTo(disposable)
    }

    private fun openShoppingListActivity(shoppingList: ShoppingList? = null) {
        val intent = Intent(activity, ShoppingListActivity::class.java).apply {
            putExtra(ShoppingListActivity.SHOPPING_LIST, shoppingList)
        }
        startActivity(intent)
    }

    private fun onAddButtonClick() {
        binding.btnAdd.setOnClickListener {
            openShoppingListActivity()
        }
    }

    companion object {
        fun newInstance(listState: ListState) = ListsFragment().apply {
            arguments = bundleOf(
                LIST_STATE to listState
            )
        }

        private val ERROR_TAG = ListFragment::class.java.simpleName
        private const val LIST_STATE = "list_state"
    }

}

@BindingAdapter("fabVisibility")
fun FloatingActionButton.fabVisibility(listState: ListState?) {
    listState?.let {
        visibility = when (listState) {
            ListState.ACTIVE -> View.VISIBLE
            ListState.ARCHIVE -> View.GONE
        }
    }
}

@BindingAdapter("btnVisibility")
fun Button.btnVisibility(listState: ListState?){
    listState?.let {
        visibility = when (listState) {
            ListState.ACTIVE -> View.VISIBLE
            ListState.ARCHIVE -> View.GONE
        }
    }
}