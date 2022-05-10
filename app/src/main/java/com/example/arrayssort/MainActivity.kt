package com.example.arrayssort

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.example.arrayssort.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSpinner()
        viewModel.subscribe().observe(this) { renderData(it) }


        binding.btnStart.setOnClickListener {
            val volume = binding.volumeSort.text.toString().toInt()
            val type = binding.spinnerTypeSort.selectedItem.toString()
            viewModel.getData(volume, type)
        }
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.sort_type,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerTypeSort.adapter = it
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.progressBar.visibility = View.INVISIBLE
                binding.containerForResult.addView(AppCompatTextView(this).apply {
                    text = appState.time
                    textSize = resources.getDimension(R.dimen.size)
                })
            }
        }
    }
}