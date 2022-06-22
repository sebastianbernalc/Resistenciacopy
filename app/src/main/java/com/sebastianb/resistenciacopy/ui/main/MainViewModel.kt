package com.sebastianb.resistenciacopy.ui.main

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.sebastianb.resistenciacopy.R
import kotlin.math.pow
import android.widget.Toast.makeText as makeText1

class MainViewModel: ViewModel() {



    private val _resistance : MutableLiveData<String> = MutableLiveData()
    val resistance : LiveData<String> = _resistance

    private val _fieldError : MutableLiveData<String> = MutableLiveData()
    val fieldError : LiveData<String> = _fieldError

    private val _bgColor1 : MutableLiveData<Int> = MutableLiveData()
    val bgColor1 : LiveData<Int> = _bgColor1

    private val _bgColor2 : MutableLiveData<Int> = MutableLiveData()
    val bgColor2 : LiveData<Int> = _bgColor2

    private val _bgColor3 : MutableLiveData<Int> = MutableLiveData()
    val bgColor3 : LiveData<Int> = _bgColor3

    private val _bgColor5 : MutableLiveData<Int> = MutableLiveData()
    val bgColor5 : LiveData<Int> = _bgColor5

    fun backGroundColor1(colorsArray: IntArray, position: Int) {
        _bgColor1.value = colorsArray[position]
    }

    fun backGroundColor2(colorsArray: IntArray, position: Int) {
        _bgColor2.value = colorsArray[position]
    }

    fun backGroundColor3(colorsArray: IntArray, position: Int) {
        _bgColor3.value = colorsArray[position]
    }

    fun backGroundColor5(colorsArray: IntArray, position: Int) {
        _bgColor5.value = colorsArray[position]
    }


    fun calculateResistance(color1:Int, color2:Int, color3:Int, color4:Int){
        var result: String
        val res =
            (((color1.toDouble() * 10.0) + (color2.toDouble())) * 10.0.pow(color3.toDouble()))
        if (color1 == -1 || color2 == -1 || color3 == -1){
            _fieldError.value = "Please fill in all fields"

        }else{
            result = when {
                res < 1000 -> {
                    res.toString().plus(" " + "Ohmios")
                }
                (res >= 1000) and (res <= 1000000) -> {
                    (res / 1000).toString().plus(" " + "Kiloohmios")
                }
                else -> {
                    (res / 1000000).toString().plus(" " + "Megaohmios")
                }
            }
            if (color4 == 0){
                result = "$result\nTolerancia de: 5%"
            }else{
                result = "$result\nTolerancia de 10%"
            }
            _resistance.value = result
        }
    }


}


