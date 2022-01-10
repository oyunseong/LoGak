package com.oys.logak.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oys.logak.ui.ImprintingModel

class HomeViewModel : ViewModel() {
    private val _imprintingModels = MutableLiveData<Set<ImprintingModel>>()
    val imprintingModels: LiveData<Set<ImprintingModel>> = _imprintingModels

    fun setUiModel(set: Set<ImprintingModel>) {
        _imprintingModels.value = set
    }
}