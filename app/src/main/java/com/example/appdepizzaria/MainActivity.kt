package com.example.appdepizzaria

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
    var clicked = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        // Configura RecyclerView
        val recyclerViewProducts = binding.recyclerViewProducts
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.setHasFixedSize(true)

        productAdapter = ProductAdapter(this, productsList)
        recyclerViewProducts.adapter = productAdapter

        // Coleta de produtos e atualização do RecyclerView
        CoroutineScope(Dispatchers.IO).launch {
            products.getProducts().collectIndexed { index, value ->
                withContext(Dispatchers.Main) {
                    productsList.addAll(value)
                    productAdapter.notifyDataSetChanged()
                    recyclerViewProducts.visibility = View.VISIBLE // Exibe o RecyclerView
                }
            }
        }

        // Configuração de clique nos botões de categoria
        binding.btnTodas.setOnClickListener {
            clicked = true
            if (clicked) {
                updateButtonStyles(
                    selectedButton = binding.btnTodas,
                    unselectedButtons = listOf(binding.btnFrango, binding.btnPizza, binding.btnEsfiha)
                )
                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtTitulo.text = "Todas"
            }
        }

        binding.btnFrango.setOnClickListener {
            clicked = true
            if (clicked) {
                updateButtonStyles(
                    selectedButton = binding.btnFrango,
                    unselectedButtons = listOf(binding.btnTodas, binding.btnPizza, binding.btnEsfiha)
                )
                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtTitulo.text = "Frango"
            }
        }

        binding.btnPizza.setOnClickListener {
            clicked = true
            if (clicked) {
                updateButtonStyles(
                    selectedButton = binding.btnPizza,
                    unselectedButtons = listOf(binding.btnTodas, binding.btnFrango, binding.btnEsfiha)
                )
                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtTitulo.text = "Pizza"
            }
        }

        binding.btnEsfiha.setOnClickListener {
            clicked = true
            if (clicked) {
                updateButtonStyles(
                    selectedButton = binding.btnEsfiha,
                    unselectedButtons = listOf(binding.btnTodas, binding.btnFrango, binding.btnPizza)
                )
                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtTitulo.text = "Esfiha"
            }
        }
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