package com.szeptun.shoppinglist.ui.Lists

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.szeptun.shoppinglist.databinding.FragmentListBinding
import com.szeptun.shoppinglist.entity.ListState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

//TODO PRZEKAZAC LISTSTATE DO VIEWMODEL - PROVIDE DI
class ListsFragment(val listState: ListState) : Fragment() {

    @Inject
    lateinit var viewModel: ListViewModel

    private lateinit var binding: FragmentListBinding
    private lateinit var listAdapter: ShoppingListAdapter
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentListBinding.inflate(inflater).apply {
            binding = this
            observeItems()
            observeItemClicks()
            setupRecycler()
        }.root


    private fun FragmentListBinding.setupRecycler() {
        with(rvList) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }

    private fun observeItems() {
        viewModel.shoppingListItemsStream
            .subscribe({
                listAdapter.items = it
            }, {
                Log.e(ERROR_TAG, "Error while observing adapter items")
            }).addTo(disposable)
    }

    private fun observeItemClicks() {
        listAdapter.selectedItemStream
            .subscribe({
                val selectedItem = listAdapter.items[it]
                //TODO OTWORZ FRAGMENT
            }, {
                Log.e(ERROR_TAG, "Error while observing RV clicks")
            })
            .addTo(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    companion object {
        fun newInstance(listState: ListState) = ListsFragment(listState)
        private val ERROR_TAG = ListFragment::class.java.simpleName
    }

}

@BindingAdapter("fabVisibility")
fun FloatingActionButton.fabVisibility(listState: ListState) {
    visibility = when (listState) {
        ListState.ACTIVE -> View.VISIBLE
        ListState.ARCHIVE -> View.GONE
    }
}