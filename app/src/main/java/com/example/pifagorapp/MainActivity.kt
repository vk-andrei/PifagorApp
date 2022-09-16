package com.example.pifagorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import com.example.pifagorapp.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var bindingClass: ActivityMainBinding
    //private var result: String? = null
    private var resultStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        setListeners()
    }

    private fun setListeners() {
        bindingClass.btnCalc.setOnClickListener {
            if (checkInputs()) {
                resultStr = "${getString(R.string.result)} ${calculate()}"
                bindingClass.tvResult.text = resultStr
                bindingClass.tvResult.visibility = View.VISIBLE
            }
        }
    }

    private fun checkInputs(): Boolean {
        bindingClass.apply {
            if (etA.text.isNullOrEmpty()) etA.error = "WTF?????"
            if (etB.text.isNullOrEmpty()) etB.error = "WTF?????"
            return (!etA.text.isNullOrEmpty() && !etB.text.isNullOrEmpty())
        }
    }

    private fun calculate(): String {
        val a = bindingClass.etA.text.toString().toDouble()
        val b = bindingClass.etB.text.toString().toDouble()
        val c = kotlin.math.sqrt(a.pow(2) + b.pow(2))
        return String.format("%.2f", c)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("TAG", "resultSTR: $resultStr")
        outState.putString("Result", resultStr)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        resultStr = savedInstanceState.getString("Result")
        bindingClass.tvResult.visibility = View.VISIBLE
        bindingClass.tvResult.text = resultStr
    }
}
