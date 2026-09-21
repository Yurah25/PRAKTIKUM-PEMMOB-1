package com.yurahco.yusufcoba.data.model

import android.content.ClipDescription
import kotlinx.serialization.descriptors.SerialDescriptor

data class Category(
    val id: Int,
    val name: String,
    val description: String?,
    val products_count: Int?
)

data class Product(
    val id: Int,
    val category_id: Int,
    val category: Category?,
    val name:String,
    val description: String?,
    val price: Double,
    val stock: Int,
    val img: String
)