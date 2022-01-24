package com.oys.logak.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _model = MutableLiveData<List<ImprintingModel>>()
    val model: LiveData<List<ImprintingModel>> = _model

    fun setUiModel(map: Map<String, Int>) {
        val list = mutableListOf<ImprintingModel>()


        // 검색 : kotlin stream 함수
        // 멀티스레드에서 좋음
//        list.filter {
//            it.text == "각인"
//        }.let {
//            _model.value = it
//        }

        map.forEach { (key, score) ->
            val imprinting = ImprintingModel(key, score)
            list.add(imprinting)
        }
        _model.value = list
    }
}