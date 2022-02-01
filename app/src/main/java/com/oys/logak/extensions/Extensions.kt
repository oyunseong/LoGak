package com.oys.logak.extensions

import android.util.Log

fun String.log() {
    Log.d("Extensions Function", this)
}

fun String.log(tag:String){
    Log.d(tag,this)
}