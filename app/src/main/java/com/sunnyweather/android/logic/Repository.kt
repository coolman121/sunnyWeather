package com.sunnyweather.android.logic
import android.util.Log
import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.model.Place
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

import kotlin.Exception
import kotlin.coroutines.CoroutineContext


object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
    val  result=try {
              val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
    if (placeResponse.status == "ok") {
        Log.d("a",placeResponse.status)
            val places = placeResponse.places
        Log.d("bb",placeResponse.places.get(1).toString())
        Result.success(places)
    } else {
        Result.failure<List<Place>>(RuntimeException("response status is${placeResponse.status}"))
       // Log.d("eeeeeeeeee",placeResponse.places.get(1).toString())
    }
}catch (e:Exception)
    {
        Result.failure<List<Place>>(e)
    }
        emit(result)
    }


//    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
//        liveData<Result<T>>(context) {
//            val result = try {
//                block()
//            } catch (e: Exception) {
//                Result.failure<T>(e)
//            }
//            emit(result)
//        }
}