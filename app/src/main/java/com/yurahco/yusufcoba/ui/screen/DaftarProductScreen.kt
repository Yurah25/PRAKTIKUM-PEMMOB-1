package com.yurahco.yusufcoba.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yurahco.yusufcoba.R
import com.yurahco.yusufcoba.data.model.Category
import com.yurahco.yusufcoba.data.model.Product
import com.yurahco.yusufcoba.ui.viewmodel.ProductUiState
import com.yurahco.yusufcoba.ui.viewmodel.ProductViewModel
import com.yurahco.yusufcoba.util.JualanConstants.BASE_URL

@Composable
fun ProductItemCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surface
        )
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            val imageModel: Any =
                if (
                    product.img ==
                    "dummy_product"
                ) {
                    R.drawable.dummy_product
                } else {
                    "${BASE_URL}img/${product.img}"
                }

            Box(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                AsyncImage(
                    model = imageModel,
                    contentDescription =
                        product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
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

                product.category?.let {
                        category ->

                    Box(
                        modifier = Modifier
                            .align(
                                Alignment.TopEnd
                            )
                            .padding(4.dp)
                            .clip(
                                RoundedCornerShape(
                                    4.dp
                                )
                            )
                            .background(
                                MaterialTheme
                                    .colorScheme
                                    .secondary
                            )
                    ) {

                        Text(
                            text =
                                category.name,
                            style =
                                MaterialTheme
                                    .typography
                                    .labelSmall,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSecondary,
                            modifier =
                                Modifier.padding(
                                    horizontal =
                                        6.dp,
                                    vertical =
                                        2.dp
                                )
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text = product.name,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium,
                fontWeight =
                    FontWeight.Bold,
                maxLines = 1,
                overflow =
                    TextOverflow.Ellipsis
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    "Rp ${product.price}",
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,
                color =
                    MaterialTheme
                        .colorScheme
                        .primary
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (isSelected) {
            MaterialTheme
                .colorScheme.primary
        } else {
            MaterialTheme
                .colorScheme
                .surfaceVariant
        }

    val textColor =
        if (isSelected) {
            MaterialTheme
                .colorScheme.onPrimary
        } else {
            MaterialTheme
                .colorScheme
                .onSurfaceVariant
        }

    Card(
        modifier =
            Modifier.clickable {
                onClick()
            },
        colors =
            CardDefaults.cardColors(
                containerColor =
                    backgroundColor
            ),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
    ) {

        Text(
            text = category.name,
            modifier =
                Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                ),
            color = textColor,
            fontWeight =
                FontWeight.Bold
        )
    }
}

@Composable
fun DaftarProdukScreen(
    navController: NavController? = null,
    viewModel: ProductViewModel
) {

    var selectedCategoryId
            by rememberSaveable {
                mutableStateOf<Int?>(null)
            }

    var searchQuery
            by rememberSaveable {
                mutableStateOf("")
            }

    val uiState
            by viewModel.uiState
                .collectAsState()

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

            if (
                selectedCategoryId == null &&
                state.categories.isNotEmpty()
            ) {
                selectedCategoryId =
                    state.categories
                        .first()
                        .id
            }

            val filteredByCategory =
                if (
                    selectedCategoryId !=
                    null
                ) {
                    state.products.filter {
                        it.category_id ==
                                selectedCategoryId
                    }
                } else {
                    state.products
                }

            val filteredProducts =
                if (
                    searchQuery.isBlank()
                ) {
                    filteredByCategory
                } else {
                    filteredByCategory
                        .filter {
                            it.name.contains(
                                searchQuery,
                                ignoreCase =
                                    true
                            )
                        }
                }

            StatelessDaftarProduct(
                categories =
                    state.categories,
                selectedCategoryId =
                    selectedCategoryId,
                onCategorySelected = {
                    selectedCategoryId =
                        it
                },
                searchQuery =
                    searchQuery,
                onSearchQueryChange = {
                    searchQuery = it
                },
                isLoading = false,
                products =
                    filteredProducts,
                onProductClick = {
                        product ->

                    navController?.navigate(
                        route =
                            "detail/${product.id}"
                    )
                },
                onContactUsClick = {

                    navController?.navigate(
                        route =
                            "hubungi_kami"
                    )
                }
            )
        }
    }
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
fun StatelessDaftarProduct(
    categories: List<Category>,
    selectedCategoryId: Int?,
    onCategorySelected: (Int) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    isLoading: Boolean,
    products: List<Product>,
    onProductClick: (Product) -> Unit,
    onContactUsClick: () -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Daftar Produk UMKM"
                    )
                },
                actions = {

                    IconButton(
                        onClick = {}
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default
                                    .ShoppingCart,
                            contentDescription =
                                "Keranjang"
                        )
                    }

                    IconButton(
                        onClick = {
                            expanded = true
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default
                                    .MoreVert,
                            contentDescription =
                                "More"
                        )
                    }

                    DropdownMenu(
                        expanded =
                            expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {

                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Hubungi Kami"
                                )
                            },
                            onClick = {
                                expanded =
                                    false
                                onContactUsClick()
                            },
                            leadingIcon = {

                                Icon(
                                    imageVector =
                                        Icons.Default
                                            .Email,
                                    contentDescription =
                                        "Email"
                                )
                            }
                        )
                    }
                },
                colors =
                    TopAppBarDefaults
                        .topAppBarColors(
                            containerColor =
                                MaterialTheme
                                    .colorScheme
                                    .primary,
                            titleContentColor =
                                MaterialTheme
                                    .colorScheme
                                    .onPrimary,
                            actionIconContentColor =
                                MaterialTheme
                                    .colorScheme
                                    .onPrimary
                        )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues
                )
        ) {

            OutlinedTextField(
                value =
                    searchQuery,
                onValueChange =
                    onSearchQueryChange,
                label = {
                    Text(
                        "Cari produk..."
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal =
                            16.dp,
                        vertical =
                            8.dp
                    ),
                singleLine = true
            )

            Text(
                text =
                    "Kategori Produk",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge,
                modifier =
                    Modifier.padding(
                        16.dp
                    )
            )

            LazyRow(
                contentPadding =
                    PaddingValues(
                        horizontal =
                            16.dp
                    ),
                horizontalArrangement =
                    Arrangement
                        .spacedBy(8.dp)
            ) {

                items(
                    categories
                ) { category ->

                    CategoryItem(
                        category =
                            category,
                        isSelected =
                            category.id ==
                                    selectedCategoryId,
                        onClick = {
                            onCategorySelected(
                                category.id
                            )
                        }
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(
                        16.dp
                    )
            )

            Text(
                text =
                    "Daftar Produk",
                style =
                    MaterialTheme
                        .typography
                        .titleLarge,
                modifier =
                    Modifier.padding(
                        horizontal =
                            16.dp,
                        vertical =
                            8.dp
                    )
            )

            if (isLoading) {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else if (
                products.isEmpty()
            ) {

                Box(
                    modifier =
                        Modifier
                            .fillMaxSize(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        "Produk tidak ditemukan."
                    )
                }

            } else {

                LazyVerticalGrid(
                    columns =
                        GridCells
                            .Fixed(2),
                    contentPadding =
                        PaddingValues(
                            16.dp
                        ),
                    horizontalArrangement =
                        Arrangement
                            .spacedBy(
                                16.dp
                            ),
                    verticalArrangement =
                        Arrangement
                            .spacedBy(
                                16.dp
                            ),
                    modifier =
                        Modifier
                            .fillMaxSize()
                ) {

                    items(
                        products
                    ) { product ->

                        ProductItemCard(
                            product =
                                product,
                            onClick = {
                                onProductClick(
                                    product
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}