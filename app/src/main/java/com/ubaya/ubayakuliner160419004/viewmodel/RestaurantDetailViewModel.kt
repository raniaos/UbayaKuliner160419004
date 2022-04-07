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

class RestaurantDetailViewModel (application: Application) : AndroidViewModel(application) {
    val resDetailLiveData = MutableLiveData<Restaurant>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refreshDetail(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        var id_res = Integer.parseInt(id)
        var username = GlobalData.username
        val url = "http://192.168.0.195/anmp/uts/get_restaurant.php?id=$id_res&username=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val results = Gson().fromJson<Restaurant>(it, Restaurant::class.java)
                resDetailLiveData.value = results
                Log.d("showvolley", it)
            },
            {
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