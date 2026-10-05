package com.yurahco.yusufcoba.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yurahco.yusufcoba.R
import com.yurahco.yusufcoba.data.model.Product
import com.yurahco.yusufcoba.ui.viewmodel.ProductUiState
import com.yurahco.yusufcoba.ui.viewmodel.ProductViewModel
import com.yurahco.yusufcoba.util.JualanConstants.BASE_URL

@Composable
fun DetailProductScreen(
    productId: Int,
    navController: NavController,
    viewModel: ProductViewModel
) {

    val context =
        LocalContext.current

    val uiState
            by viewModel.uiState
                .collectAsState()

    var quantity
            by rememberSaveable {
                mutableStateOf(1)
            }

    when (val state = uiState) {

        ProductUiState.Loading -> {

            Box(
                modifier =
                    Modifier.fillMaxSize(),
                contentAlignment =
                    Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ProductUiState.Error -> {

            Box(
                modifier =
                    Modifier.fillMaxSize(),
                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "Error: ${state.message}",
                    color =
                        MaterialTheme
                            .colorScheme
                            .error
                )
            }
        }

        is ProductUiState.Success -> {

            val product =
                state.products.find {
                    it.id ==
                            productId
                }

            if (product == null) {

                Box(
                    modifier =
                        Modifier.fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        "Produk tidak ditemukan"
                    )
                }

            } else {

                StatelessDetailProduct(
                    product =
                        product,
                    quantity =
                        quantity,
                    onQuantityChange = {
                            newQuantity ->

                        quantity =
                            newQuantity
                    },
                    onBackClick = {
                        navController
                            .popBackStack()
                    },
                    onAddToCartClick = {

                        Toast.makeText(
                            context,
                            "Membeli sebanyak $quantity",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun StatelessDetailProduct(
    product: Product?,
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Detail Produk"
                    )
                },
                navigationIcon = {

                    IconButton(
                        onClick =
                            onBackClick
                    ) {

                        Icon(
                            painter =
                                painterResource(
                                    id =
                                        R.drawable
                                            .back_icon
                                ),
                            contentDescription =
                                "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (product != null) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        paddingValues
                    )
                    .padding(
                        24.dp
                    )
            ) {

                val imageModel: Any =
                    if (
                        product.img ==
                        "dummy_product"
                    ) {
                        R.drawable
                            .dummy_product
                    } else {
                        "${BASE_URL}img/${product.img}"
                    }

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                ) {

                    AsyncImage(
                        model =
                            imageModel,
                        contentDescription =
                            product.name,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(
                                    1f
                                )
                                .clip(
                                    RoundedCornerShape(
                                        8.dp
                                    )
                                )
                                .background(
                                    Color.White
                                ),
                        contentScale =
                            ContentScale.Fit
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            16.dp
                        )
                )

                Text(
                    text =
                        product.name,
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    fontWeight =
                        FontWeight.Bold
                )

                Text(
                    text =
                        "Rp ${product.price}",
                    style =
                        MaterialTheme
                            .typography
                            .bodyLarge
                )

                Text(
                    text =
                        "Kategori: ${product.category?.name ?: "-"}",
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

                Text(
                    text =
                        "Stok: ${product.stock}",
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

                Text(
                    text =
                        product.description
                            ?: "-",
                    style =
                        MaterialTheme
                            .typography
                            .bodyMedium
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            24.dp
                        )
                )

                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement
                            .SpaceBetween,
                    verticalAlignment =
                        Alignment
                            .CenterVertically
                ) {

                    Text(
                        text =
                            "Jumlah",
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Row(
                        verticalAlignment =
                            Alignment
                                .CenterVertically
                    ) {

                        FilledTonalIconButton(
                            onClick = {

                                if (
                                    quantity > 1
                                ) {
                                    onQuantityChange(
                                        quantity - 1
                                    )
                                }
                            }
                        ) {
                            Text("-")
                        }

                        Text(
                            text =
                                quantity
                                    .toString(),
                            modifier =
                                Modifier
                                    .padding(
                                        horizontal =
                                            16.dp
                                    )
                        )

                        FilledTonalIconButton(
                            onClick = {

                                if (
                                    quantity <
                                    product.stock
                                ) {
                                    onQuantityChange(
                                        quantity + 1
                                    )
                                }
                            },
                            enabled =
                                quantity <
                                        product.stock
                        ) {
                            Text("+")
                        }
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            16.dp
                        )
                )

                Button(
                    onClick =
                        onAddToCartClick,
                    modifier =
                        Modifier
                            .fillMaxWidth(),
                    enabled =
                        product.stock > 0
                ) {

                    Text(
                        "Tambahkan ke Keranjang"
                    )
                }
            }
        }
    }
}