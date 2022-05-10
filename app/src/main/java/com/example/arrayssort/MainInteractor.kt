package com.example.arrayssort

import java.util.*

class MainInteractor : Interactor {
    override suspend fun getData(length: Int, type: String): AppState {
        val appState: AppState = when (type) {
            "Обмен" -> AppState.Success(startSwap(length))
            "Включения" -> AppState.Success(startInsert(length))
            "Шелла" -> AppState.Success(startShell(length))
            "Блочная" -> AppState.Success(startBucket(length))
            else -> AppState.Success(startSwap(length))
        }
        return appState
    }

    private fun startSwap(length: Int): String {
        val arr = randomArray(length)
        val timeStart = System.currentTimeMillis()
        for (i in 1 until length) {
            var j = i
            while (j > 0 && arr[j - 1] > arr[j]) {
                val x = arr[j - 1]
                arr[j - 1] = arr[j]
                arr[j] = x
                j--
            }
        }
        val timeEnd = System.currentTimeMillis() - timeStart
        return "$timeEnd мс - Обмен"
    }

    private fun startInsert(length: Int): String {
        val arr = randomArray(length)
        val timeStart = System.currentTimeMillis()
        for (i in 2 until length) {
            for (j in length - 1 downTo i) {
                if (arr[j - 1] > arr[j]) {
                    val x = arr[j - 1]
                    arr[j - 1] = arr[j]
                    arr[j] = x
                }
            }
        }
        val timeEnd = System.currentTimeMillis() - timeStart
        return "$timeEnd мс - Включения"
    }

    private fun startShell(length: Int): String {
        val arr = randomArray(length)
        val timeStart = System.currentTimeMillis()
        var step: Int = length / 2
        while (step > 0) {
            for (i in step until length) {
                val temp: Int = arr[i]
                var j = i
                while (j >= step && arr[j - step] > temp) {
                    arr[j] = arr[j - step]
                    j -= step
                }
                arr[j] = temp
            }
            step /= 2
        }
        val timeEnd = System.currentTimeMillis() - timeStart
        return "$timeEnd мс - Шелла"
    }

    private fun startBucket(length: Int): String {
        val arr = randomArray(length)
        val timeStart = System.currentTimeMillis()
        val bucket = IntArray(length + 1)
        Arrays.fill(bucket, 0)
        var i = 0
        while (i < length) {
            bucket[arr[i]]++
            i++
        }
        var k = 0
        i = 0
        while (i < bucket.size) {
            var j = 0
            while (j < bucket[i]) {
                arr[k++] = i
                j++
            }
            i++
        }
        val timeEnd = System.currentTimeMillis() - timeStart
        return "$timeEnd мс - Блочная"
    }

    private fun randomArray(length: Int): IntArray {
        val arr = IntArray(length)
        for (i in 0 until length)
            arr[i] = (0..1000).random()
        return arr
    }
}