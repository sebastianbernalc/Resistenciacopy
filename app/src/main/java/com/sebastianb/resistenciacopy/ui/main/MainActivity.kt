package com.sebastianb.resistenciacopy.ui.main

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sebastianb.resistenciacopy.R
import com.sebastianb.resistenciacopy.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding  //Declaramos pero no la inicializamos
    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val colors = resources.getIntArray(R.array.colrs)
        val tolerance = resources.getIntArray(R.array.toler)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.resistance.observe(this) { res ->
            mainBinding.resultado.text = res
        }

        mainViewModel.bgColor1.observe(this) { color ->
            mainBinding.spinner1.setBackgroundColor(color)
        }

        mainViewModel.bgColor2.observe(this) { color ->
            mainBinding.spinner2.setBackgroundColor(color)
        }

        mainViewModel.bgColor3.observe(this) { color ->
            mainBinding.spinner3.setBackgroundColor(color)
        }

        mainViewModel.bgColor5.observe(this) { color ->
            mainBinding.spinner5.setBackgroundColor(color)
        }

        mainViewModel.fieldError.observe(this) { err ->
            val toast = Toast.makeText(this, err, Toast.LENGTH_SHORT)
            toast.show()
        }


        mainBinding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) =
                mainBinding.spinner1.setBackgroundColor(resources.getColor(R.color.white))
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                mainViewModel.backGroundColor1(colors, position)
                //mainBinding.spinner1.setBackgroundColor(colors[mainBinding.spinner1.selectedItemId.toInt()])
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

                mainViewModel.backGroundColor2(colors, position)
                //mainBinding.spinner2.setBackgroundColor(colors[mainBinding.spinner2.selectedItemId.toInt()])
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

                mainViewModel.backGroundColor3(colors, position)
                //mainBinding.spinner3.setBackgroundColor(colors[mainBinding.spinner3.selectedItemId.toInt()])
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
                mainViewModel.backGroundColor5(tolerance, position)
                //mainBinding.spinner5.setBackgroundColor(tolerance[mainBinding.spinner5.selectedItemId.toInt()])
            }
        }

        with(mainBinding) {
            buttonCalc.setOnClickListener {
                val color1 = spinner1.selectedItemId.toInt() - 1
                val color2 = spinner2.selectedItemId.toInt() - 1
                val color3 = spinner3.selectedItemId.toInt() - 1
                val color4 = spinner5.selectedItemId.toInt() - 1


                mainViewModel.calculateResistance(color1, color2, color3, color4)


            }
        }
    }
}