package com.example.appdepizzaria

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appdepizzaria.adapter.ProductAdapter
import com.example.appdepizzaria.databinding.ActivityMainBinding
import com.example.appdepizzaria.listitems.Products
import com.example.appdepizzaria.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val products = Products()
    private val productsList: MutableList<Product> = mutableListOf()
    private val filteredProducts: MutableList<Product> = mutableListOf()
    var clicked = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val recyclerViewProducts = binding.recyclerViewProducts
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.setHasFixedSize(true)

        productAdapter = ProductAdapter(this, productsList)
        recyclerViewProducts.adapter = productAdapter

        CoroutineScope(Dispatchers.IO).launch {
            products.getProducts().collectIndexed { index, value ->
                withContext(Dispatchers.Main) {
                    productsList.addAll(value)
                    productAdapter.notifyDataSetChanged()
                    recyclerViewProducts.visibility = View.VISIBLE // Exibe o RecyclerView
                }
            }
        }

        binding.searchViewProducts.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                callSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                callSearch(newText)
                //              }
                return true
            }

            fun callSearch(query: String?) {

                CoroutineScope(Dispatchers.IO).launch {
                    products.getProducts().collectIndexed { index, value ->
                        withContext(Dispatchers.Main) {
                            productsList.clear()
                            productsList.addAll(value.filter { it.name.contains(query.toString(), ignoreCase = true) })
                            productAdapter.notifyDataSetChanged()
                            recyclerViewProducts.visibility = View.VISIBLE
                        }
                    }
                }
                }
            });


    }

    // Método para atualizar os estilos dos botões
    private fun updateButtonStyles(selectedButton: View, unselectedButtons: List<View>) {
        selectedButton.setBackgroundResource(R.drawable.bg_button_enabled)
        selectedButton.setBackgroundColor(Color.WHITE)
        for (button in unselectedButtons) {
            button.setBackgroundResource(R.drawable.bg_button_disabled)
            button.setBackgroundColor(Color.DKGRAY)
        }
    }
}