package com.expandable.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.expandable.recyclerview.model.ListDataModel

class ActivityMainViewModel: ViewModel() {
    var list: MutableLiveData<List<ListDataModel>> = MutableLiveData<List<ListDataModel>>()
}