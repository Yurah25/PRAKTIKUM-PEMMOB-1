package com.yurahco.yusufcoba.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yurahco.yusufcoba.R
import com.yurahco.yusufcoba.data.dummy.DummyData
import com.yurahco.yusufcoba.data.model.Product
import kotlinx.coroutines.delay

@Composable
fun DetailProductScreen(
    productId: Int,
    navController: NavController
) {
    val context = LocalContext.current

    var isLoading by remember {
        mutableStateOf(true)
    }

    var product by remember {
        mutableStateOf<Product?>(null)
    }

    var quantity by rememberSaveable {
        mutableStateOf(1)
    }

    LaunchedEffect(productId) {
        isLoading = true

        delay(1000)

        product = DummyData.products.find {
            it.id == productId
        }

        isLoading = false
    }

    StatelessDetailProduct(
        product = product,
        isLoading = isLoading,
        quantity = quantity,
        onQuantityChange = {
            quantity = it
        },
        onBackClick = {
            navController.popBackStack()
        },
        onAddToCartClick = {
            Toast.makeText(
                context,
                "Produk ditambahkan ke keranjang",
                Toast.LENGTH_SHORT
            ).show()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatelessDetailProduct(
    product: Product?,
    isLoading: Boolean,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detail Produk")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.back_icon
                            ),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (product != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.dummy_product
                    ),
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Rp ${product.price}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Kategori: ${product.category?.name ?: "-"}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Stok: ${product.stock}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = product.description ?: "-",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Jumlah",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilledTonalIconButton(
                            onClick = {
                                if (quantity > 1) {
                                    onQuantityChange(quantity - 1)
                                }
                            }
                        ) {
                            Text("-")
                        }

                        Text(
                            text = quantity.toString(),
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )

                        FilledTonalIconButton(
                            onClick = {
                                if (quantity < product.stock) {
                                    onQuantityChange(quantity + 1)
                                }
                            },
                            enabled = quantity < product.stock
                        ) {
                            Text("+")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onAddToCartClick,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = product.stock > 0
                ) {
                    Text("Tambahkan ke Keranjang")
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Produk tidak ditemukan")
            }
        }
    }
}