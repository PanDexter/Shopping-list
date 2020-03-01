package com.szeptun.shoppinglist.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String
) : Parcelable