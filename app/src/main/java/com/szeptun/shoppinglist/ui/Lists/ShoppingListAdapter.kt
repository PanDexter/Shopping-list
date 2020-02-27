package com.szeptun.shoppinglist.ui.Lists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemShoppinglistBinding
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

//TODO
class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListViewHolder>() {

    var items: List<ShoppingList> = arrayListOf()
    private var selectedItemSubject: PublishSubject<Int> = PublishSubject.create()
    val selectedItemStream: Observable<Int> = selectedItemSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemShoppinglistBinding.inflate(inflater, parent, false)
        return ShoppingListViewHolder(binding = binding, onItemClicked = selectedItemSubject)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(item = items[position])
    }
}