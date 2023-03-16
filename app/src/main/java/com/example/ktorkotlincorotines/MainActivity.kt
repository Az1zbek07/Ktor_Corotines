package com.example.ktorkotlincorotines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktorkotlincorotines.adapter.UserAdapter
import com.example.ktorkotlincorotines.databinding.ActivityMainBinding
import com.example.ktorkotlincorotines.network.KtorClient
import com.example.ktorkotlincorotines.network.KtorService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val userAdapter by lazy { UserAdapter() }
    private val ktorService by lazy { KtorService }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.pr.isVisible = true
        loadUsers()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadUsers(){
        binding.rv.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        GlobalScope.launch(Dispatchers.Main) {
            userAdapter.submitList(ktorService.getAllUsers())
        }
        userAdapter.onClick = {
            val bundle = bundleOf("id" to it)
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        binding.pr.isVisible = false
    }
}