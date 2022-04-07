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
import com.ubaya.ubayakuliner160419004.model.Review

class ReviewViewModel (application: Application) : AndroidViewModel(application) {
    val reviewLiveData = MutableLiveData<ArrayList<Review>>()
    val reviewLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refreshReview(id: String) {
        reviewLoadErrorLiveData.value = false
        loadingLiveData.value = true

        Log.d("showvolley", "$id")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.195/anmp/uts/get_review.php?id=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Review>>() {}.type
                val result = Gson().fromJson<ArrayList<Review>>(it, sType)
                Log.d("showvolley", "$result")
                reviewLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                reviewLoadErrorLiveData.value = true
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