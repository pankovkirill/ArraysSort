package com.example.arrayssort

sealed class AppState {
    data class Success(val time: String) : AppState()
    object Loading : AppState()
}