package com.example.ktorkotlincorotines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.ktorkotlincorotines.databinding.ActivityDetailBinding
import com.example.ktorkotlincorotines.model.User
import com.example.ktorkotlincorotines.network.KtorService
import kotlinx.coroutines.*

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val ktorService by lazy { KtorService }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.textView.isVisible = false
        binding.textView2.isVisible = false
        binding.pr.isVisible = true

        GlobalScope.launch(Dispatchers.IO){
            val user = ktorService.getOneUser(intent.getIntExtra("id", -1))
            withContext(Dispatchers.Main){
                with(binding){
                    Glide.with(imageView).load(user.avatar).into(imageView)
                    textUsername.text = user.username
                    textNumber.text = user.phone_number
                    textLocation.text = "${user.address.country} ${user.address.city}"
                    binding.textView.isVisible = true
                    binding.textView2.isVisible = true
                    pr.isVisible = false
                }
            }
        }
    }
}