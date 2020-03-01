package com.szeptun.shoppinglist.ui.Lists.recycler

import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemShoppinglistBinding
import com.szeptun.shoppinglist.entity.ShoppingList
import io.reactivex.subjects.PublishSubject

class ShoppingListViewHolder(
    private val binding: ItemShoppinglistBinding,
    private val onItemClick: PublishSubject<Int>,
    private val onArchiveClick: PublishSubject<Int>
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var shoppingList: ShoppingList

    fun bind(item: ShoppingList) {
        shoppingList = item
        binding.listModel = shoppingList
        binding.root.setOnClickListener {
            onItemClick.onNext(adapterPosition)
        }
        binding.btnArchive?.setOnClickListener {
            onArchiveClick.onNext(adapterPosition)
        }
    }
}