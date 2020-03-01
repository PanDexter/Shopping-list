package com.szeptun.shoppinglist.ui.ShoppingList.recycler

import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemProductBinding
import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import io.reactivex.subjects.PublishSubject

class ProductViewHolder(
    private val binding: ItemProductBinding,
    private val onItemClicked: PublishSubject<Int>
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var product: Product
    lateinit var listState: ListState

    fun bind(item: Product, state: ListState) {
        product = item
        listState = state
        binding.productModel = product
        binding.listState = listState
        binding.btnRemove.setOnClickListener {
            onItemClicked.onNext(adapterPosition)
        }
    }
}