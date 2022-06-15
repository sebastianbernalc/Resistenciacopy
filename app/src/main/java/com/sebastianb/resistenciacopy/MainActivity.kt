package com.sebastianb.resistenciacopy

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.Toast
import com.sebastianb.resistenciacopy.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding  //Declaramos pero no la inicializamos
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        val view= mainBinding.root
        setContentView(view)
        val colors = resources.getIntArray(R.array.colrs)
        val tolerance = resources.getIntArray(R.array.toler)

        mainBinding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) =
                mainBinding.spinner1.setBackgroundColor(resources.getColor(R.color.white))
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainBinding.spinner1.setBackgroundColor(colors[mainBinding.spinner1.selectedItemId.toInt()])
            }
        }
        mainBinding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                mainBinding.spinner2.setBackgroundColor(resources.getColor(R.color.white))
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainBinding.spinner2.setBackgroundColor(colors[mainBinding.spinner2.selectedItemId.toInt()])
            }
        }
        mainBinding.spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                with(mainBinding) { with(spinner3) { setBackgroundColor(resources.getColor(R.color.white)) } }
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainBinding.spinner3.setBackgroundColor(colors[mainBinding.spinner3.selectedItemId.toInt()])
            }
        }
        mainBinding.spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                with(mainBinding) { with(spinner5) { setBackgroundColor(resources.getColor(R.color.white)) } }
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainBinding.spinner5.setBackgroundColor(tolerance[mainBinding.spinner5.selectedItemId.toInt()])
            }
        }
        mainBinding.buttonCalc.setOnClickListener {
            val color1 = mainBinding.spinner1.selectedItemId.toInt()-1
            val color2 = mainBinding.spinner2.selectedItemId.toInt()-1
            val color3 = mainBinding.spinner3.selectedItemId.toInt()-1
            val color4 = mainBinding.spinner5.selectedItemId.toInt()-1
            var result: String
            val res =
                (((color1.toDouble() * 10.0) + (color2.toDouble())) * 10.0.pow(color3.toDouble()))
            if (color1==-1 || color2==-1 || color3==-1){
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }else{
                when {
                    res < 1000 -> {
                        result = res.toString().plus(" " + getString(R.string.ohm))
                    }
                    (res >= 1000) and (res <= 1000000) -> {
                        result = (res / 1000).toString().plus(" " + getString(R.string.Kohm))
                    }
                    else -> {
                        result=(res / 1000000).toString().plus(" " + getString(R.string.Mohm))
                    }
                }
                if (color4 == 0){
                    result = result + "\n" + getString(R.string.Tolerancia5)
                }else{
                    result = result + "\n" + getString(R.string.Tolerancia10)
                }
                mainBinding.resultado.text = result
            }

        }
    }
}