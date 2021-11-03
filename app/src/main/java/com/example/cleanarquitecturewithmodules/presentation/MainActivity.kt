package com.example.cleanarquitecturewithmodules.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarquitecturewithmodules.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<BookViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGetBooks.setOnClickListener {
            viewModel.getBooks()
        }

        binding.buttonGetBoosById.setOnClickListener {
            //viewModel.getBook("rqe31bv1QYcEnqn008mB")
        }

        binding.buttonInsertBook.setOnClickListener {
           // viewModel.setBook()
        }

        viewModel.dataLoading.observe(this, {
            when (it) {
                true -> Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
            }
        })

       /* viewModel.books.observe(this, {
            binding.textView.text = it
        })*/
    }

}