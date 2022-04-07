package com.ubaya.ubayakuliner160419004.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayakuliner160419004.GlobalData
import com.ubaya.ubayakuliner160419004.model.Restaurant

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantLiveData = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refreshHome() {
        restaurantLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.195/anmp/uts/get_restaurant.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Restaurant>>() {}.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(it, sType)
                restaurantLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                restaurantLoadErrorLiveData.value = true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun refreshLikedRestaurant() {
        restaurantLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        var username = GlobalData.username
        val url = "http://192.168.0.195/anmp/uts/get_restaurant.php?liked=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Restaurant>>() {}.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(it, sType)
                restaurantLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                restaurantLoadErrorLiveData.value = true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}