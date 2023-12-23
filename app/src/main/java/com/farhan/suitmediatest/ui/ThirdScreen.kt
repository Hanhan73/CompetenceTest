package com.farhan.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.farhan.suitmediatest.databinding.ActivityThirdScreenBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhan.suitmediatest.response.DataItem

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel by viewModels<ThirdScreenViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        viewModel.listUser.observe(this) { User ->
            setAccountData(User)
        }

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun setAccountData(userAccount: List<DataItem?>) {
        val adapter = UserAdapter { clickedUser ->
            sendName(clickedUser.firstName ?: "")
        }
        adapter.submitList(userAccount)
        binding.rvUser.adapter = adapter
    }

    private fun sendName(name : String){
        val intent = Intent(this, SecondScreen::class.java)
        intent.putExtra(SecondScreen.USER_DATA, name)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
}