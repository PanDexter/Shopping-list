package com.szeptun.shoppinglist.commons

import com.szeptun.shoppinglist.entity.ListState
import com.szeptun.shoppinglist.entity.Product
import com.szeptun.shoppinglist.entity.ShoppingList
import org.joda.time.LocalDateTime

object DummyData {

    val dummyProducts = listOf(Product("Chleb"), Product("Maslo"), Product("Ser"), Product("Mleko"))

    val dummyShoppingList = listOf(
        ShoppingList(
            name = "Zakupy wtorek",
            listState = ListState.ACTIVE,
            date = LocalDateTime.now(),
            itemsList = listOf(dummyProducts[0])
        ),
        ShoppingList(
            name = "Zabka",
            listState = ListState.ARCHIVE,
            date = LocalDateTime.now().minusDays(2),
            itemsList = listOf(dummyProducts[1], dummyProducts[3])
        )
    )

}