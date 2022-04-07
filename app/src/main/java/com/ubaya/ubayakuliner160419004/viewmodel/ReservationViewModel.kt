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
import com.ubaya.ubayakuliner160419004.model.Reservation
import com.ubaya.ubayakuliner160419004.model.Restaurant

class ReservationViewModel(application: Application) : AndroidViewModel(application) {
    val reservationLiveData = MutableLiveData<ArrayList<Reservation>>()
    val reservationLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refreshUnfinished() {
        reservationLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        var username = GlobalData.username
        val url = "http://192.168.0.195/anmp/uts/get_reservation.php?finished=0&username=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Reservation>>() {}.type
                val result = Gson().fromJson<ArrayList<Reservation>>(it, sType)
                reservationLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                reservationLoadErrorLiveData.value = true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    fun refreshFinished() {
        reservationLoadErrorLiveData.value = false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        var username = GlobalData.username
        val url = "http://192.168.0.195/anmp/uts/get_reservation.php?finished=1&username=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Reservation>>() {}.type
                val result = Gson().fromJson<ArrayList<Reservation>>(it, sType)
                reservationLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                reservationLoadErrorLiveData.value = true
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