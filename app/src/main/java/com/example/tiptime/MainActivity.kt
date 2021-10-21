package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip()}
    }

    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
        val selectedID = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedID) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_ten_percent -> 0.10
            else -> 0.05
        }

        var tip = tipPercentage * cost

        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedTip)
    }
}