package com.chunbae.narchive.presentation.ui.search.location.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chunbae.narchive.R
import com.chunbae.narchive.data.data.LocationData
import com.chunbae.narchive.databinding.ActivitySearchLocationBinding
import com.chunbae.narchive.presentation.ui.search.location.adapter.SearchLocationAdapter
import com.chunbae.narchive.presentation.ui.search.location.viewmodel.SearchLocationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchLocationActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySearchLocationBinding
    private val viewModel : SearchLocationViewModel by viewModels()
    private val adapter by lazy {
        SearchLocationAdapter(::onLocationSelected)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_location)

        initBinidng()
        initRV()
        observe()

    }

    private fun initBinidng() {
        binding.activity = this
        binding.viewModel = viewModel
    }

    private fun initRV() {
        binding.searchLocationRvLocations.adapter = adapter
    }

    private fun observe() {
        viewModel.locationSearchData.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun onLocationSelected(location : LocationData) {
        setResult(Activity.RESULT_OK, Intent().putExtra("Location", location))
        finish()
    }

    fun dismiss() {
        finish()
    }
}