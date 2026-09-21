package com.yurahco.yusufcoba.data.dummy

import com.yurahco.yusufcoba.data.model.Category
import com.yurahco.yusufcoba.data.model.Product

object DummyData {
    val categories = listOf(
        Category(id = 1, name = "Makanan", description = "Aneka Makanan Lokal", products_count = 5),
        Category(id = 2, name = "Minuman", description = "Minuman Segar", products_count = 5),
        Category(id = 3, name = "Kerajinan", description = "Kerajinan Tangan", products_count = 5)
    )

    val products = listOf(
        Product(1, 1, categories[0], "Kripik Singkong", "Kripik Gurih", 15000.0, 50, "dummy_product"),
        Product(2, 1, categories[0], "Mendoan", "Mendoan Asli Purbalingga", 20000.0, 30, "dummy_product"),
        Product(3, 1, categories[0], "Sale Pisang", "Sale Pisang Manis", 25000.0, 20, "dummy_product"),
        Product(4, 1, categories[0], "Getuk Goreng", "Getuk Khas", 30000.0, 40, "dummy_product"),
        Product(5, 1, categories[0], "Nopia", "Nopia Rasa Coklat", 22000.0, 60, "dummy_product"),

        Product(6, 2, categories[1], "Es Dawet", "Dawet Segar", 10000.0, 100, "dummy_product"),
        Product(7, 2, categories[1], "Wedang Jahe", "Jahe Instan", 12000.0, 50, "dummy_product"),
        Product(8, 2, categories[1], "Kopi Robusta", "Kopi Bubuk", 45000.0, 20, "dummy_product"),
        Product(9, 2, categories[1], "Teh Poci", "Teh Melati", 15000.0, 40, "dummy_product"),
        Product(10, 2, categories[1], "Sirup Stroberi", "Sirup Rasa", 35000.0, 15, "dummy_product"),

        Product(11, 3, categories[2], "Batik Purbalingga", "Kain Batik", 150000.0, 10, "dummy_product"),
        Product(12, 3, categories[2], "Sandal Bandol", "Sandal awet", 40000.0, 25, "dummy_product"),
        Product(13, 3, categories[2], "Sapu Glagah", "Sapu lantai", 25000.0, 100, "dummy_product"),
        Product(14, 3, categories[2], "Gantungan Kunci", "Gantungan kayu", 5000.0, 150, "dummy_product"),
        Product(15, 3, categories[2], "Tas Rajut", "Tas wanita rajut", 85000.0, 5, "dummy_product")
    )
}
