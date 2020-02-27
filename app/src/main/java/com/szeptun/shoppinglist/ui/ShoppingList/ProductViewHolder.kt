package com.szeptun.shoppinglist.ui.ShoppingList

import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemProductBinding
import com.szeptun.shoppinglist.entity.Product
import io.reactivex.subjects.PublishSubject

class ProductViewHolder(private val binding: ItemProductBinding, private val onItemClicked: PublishSubject<Int>) :
    RecyclerView.ViewHolder(binding.root) {

    lateinit var product: Product

    fun bind(item: Product) {
        product = item
        binding.productModel = product
        binding.btnRemove.setOnClickListener {
            onItemClicked.onNext(adapterPosition)
        }
    }
}