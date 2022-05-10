package com.example.arrayssort

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class MainViewModel(private val interactor: Interactor = MainInteractor()) : ViewModel() {
    private val _data = MutableLiveData<AppState>()

    private val liveDataToObserve: LiveData<AppState> = _data

    fun subscribe() = liveDataToObserve

    fun getData(length: Int, type: String) {
        _data.postValue(AppState.Loading)
        viewModelScope.launch { startInteractor(length, type) }
    }

    private suspend fun startInteractor(length: Int, type: String) = withContext(Dispatchers.IO) {
        _data.postValue(interactor.getData(length, type))
    }
}