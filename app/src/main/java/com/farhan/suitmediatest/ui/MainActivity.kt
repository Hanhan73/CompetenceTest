package com.farhan.suitmediatest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.farhan.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnCheckPalindrome.setOnClickListener{
            checkPolidrome()
        }

        binding.btnNext.setOnClickListener{

            val name = binding.edName.text.toString()
            if (!name.isNullOrEmpty()) {
                val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.apply()
                val intent = Intent(this, SecondScreen::class.java)
                startActivity(intent)
            }else{
                showDialog("Please fill the name first!")
            }
        }

    }

    private fun checkPolidrome() {
        val input = binding.edSentence.text.toString()
        if (!input.isNullOrEmpty()){
            val cleanString = input.replace("\\s+".toRegex(), "").toLowerCase()
            if (cleanString == cleanString.reversed()) showDialog("isPalindrome") else showDialog("not palindrome")
        } else{
            showDialog("Please input the TextField first")
        }

    }

    private fun showDialog(message : String){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}