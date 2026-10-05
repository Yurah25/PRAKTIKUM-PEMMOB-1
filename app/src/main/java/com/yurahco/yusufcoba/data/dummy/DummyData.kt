package com.yurahco.yusufcoba.data.dummy

import com.yurahco.yusufcoba.data.model.Category
import com.yurahco.yusufcoba.data.model.Product

object DummyData {
    val categories = listOf(
        Category(
            id = 1,
            name = "Makanan",
            description = "Aneka makanan olahan UMKM",
            products_count = 2
        ),
        Category(
            id = 2,
            name = "Minuman",
            description = "Aneka minuman segar UMKM",
            products_count = 2
        ),
        Category(
            id = 3,
            name = "Kerajinan",
            description = "Kerajinan tangan lokal",
            products_count = 1
        )
    )

    val products = listOf(
        Product(
            id = 1,
            category_id = 1,
            category = categories[0],
            name = "Keripik Singkong Balado",
            description = "Keripik singkong renyah dengan bumbu balado pedas manis khas rumahan.",
            price = 15000.0,
            stock = 50,
            img = "dummy_product"
        ),
        Product(
            id = 2,
            category_id = 1,
            category = categories[0],
            name = "Kue Kering Nastar",
            description = "Nastar homemade dengan selai nanas asli dan mentega pilihan.",
            price = 50000.0,
            stock = 20,
            img = "dummy_product"
        ),
        Product(
            id = 3,
            category_id = 2,
            category = categories[1],
            name = "Es Teh Herbal Manis",
            description = "Teh herbal alami yang menyegarkan dahaga.",
            price = 5000.0,
            stock = 100,
            img = "dummy_product"
        ),
        Product(
            id = 4,
            category_id = 2,
            category = categories[1],
            name = "Susu Kedelai Murni",
            description = "Susu kedelai segar kaya protein tanpa pengawet.",
            price = 8000.0,
            stock = 30,
            img = "dummy_product"
        ),
        Product(
            id = 5,
            category_id = 3,
            category = categories[2],
            name = "Tas Anyaman Rotan",
            description = "Tas tangan hasil kerajinan tangan pengrajin lokal berbahan rotan.",
            price = 120000.0,
            stock = 10,
            img = "dummy_product"
        )
    )
}
