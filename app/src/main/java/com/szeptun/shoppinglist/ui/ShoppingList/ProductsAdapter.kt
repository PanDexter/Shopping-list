package com.szeptun.shoppinglist.ui.ShoppingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szeptun.shoppinglist.databinding.ItemProductBinding
import com.szeptun.shoppinglist.entity.Product
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    var items: List<Product> = arrayListOf()
    private var selectedItemSubject: PublishSubject<Int> = PublishSubject.create()
    val selectedItemStream: Observable<Int> = selectedItemSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding, selectedItemSubject)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(item = items[position])
    }

    fun setDataWithDiff(productList: List<Product>){
        this.items = productList
        notifyDataSetChanged()
    }

}