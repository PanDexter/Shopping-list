package com.szeptun.shoppinglist.ui.Lists

import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemShoppinglistBinding
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.subjects.PublishSubject

//TODO
class ShoppingListViewHolder(
    val binding: ItemShoppinglistBinding,
    val onItemClicked: PublishSubject<Int>
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var shoppingList: ShoppingList

    fun bind(item: ShoppingList) {
        shoppingList = item
        binding.listModel = shoppingList
        binding.root.setOnClickListener {
            onItemClicked.onNext(adapterPosition)
        }
    }
}