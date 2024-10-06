package com.example.isirss_examenapp.framework.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.isirss_examenapp.databinding.ActivitySplashscreenBinding
import com.example.isirss_examenapp.framework.viewmodel.SplashscreenViewModel

class SplashscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding
    private val viewModel: SplashscreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        viewModel.onCreate()

        initializeObservers()
    }

    private fun initializeBinding() {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.finishedLoading.observe(this, Observer { finishedLoading ->
            if (finishedLoading) {
                passViewGoToMain()
            }
        })
    }

    private fun passViewGoToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
}
