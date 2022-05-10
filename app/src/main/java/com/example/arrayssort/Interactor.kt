package com.example.arrayssort

interface Interactor {
    suspend fun getData(length: Int, type: String): AppState
}