package com.oys.logak

import android.util.Log
import com.google.gson.Gson
import com.oys.logak.extensions.log

class Converter {

    fun toJson(arr: Array<String>) {
        val gson = Gson()
        val jsonStr: String = gson.toJson(arr)

        jsonStr.log("++toJson")


    }


    override fun toString(): String {
        return super.toString()
    }
}