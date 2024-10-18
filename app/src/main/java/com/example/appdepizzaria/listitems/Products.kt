package com.example.appdepizzaria.listitems

import com.example.appdepizzaria.R
import com.example.appdepizzaria.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Products {

    // A lista de produtos deve ser imutável ao público externo
    private val _productList = MutableStateFlow<List<Product>>(emptyList())
    private val productListFlow: StateFlow<List<Product>> = _productList

    fun getProducts(): Flow<List<Product>> {
        // Cria a lista de produtos apenas uma vez, evitando recriação desnecessária
        val productList = listOf(
            Product(
                imgProducts = R.drawable.pizza_queijo,
                name = "Pizza de Queijo",
                price = "50,00"
            ),
            Product(
                imgProducts = R.drawable.pizza_calabresa,
                name = "Pizza de Calabresa",
                price = "45,00"
            ),
            Product(
                imgProducts = R.drawable.pizza_frango,
                name = "Pizza de Frango",
                price = "55,00"
            ),
            Product(
                imgProducts = R.drawable.pizza_portuguesa,
                name = "Pizza Portuguesa",
                price = "50,00"
            ),
            Product(
                imgProducts = R.drawable.pizza_queijo,
                name = "Pizza de Queijo",
                price = "50,00"
            )
        )
        _productList.value = productList
        return productListFlow
    }
}
