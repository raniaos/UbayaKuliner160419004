package com.ubaya.ubayakuliner160419004.viewmodel

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.ubayakuliner160419004.model.User

class UserViewModel (application: Application) : AndroidViewModel(application)  {
    val userLiveData = MutableLiveData<User>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(username: String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.0.195/anmp/uts/get_user.php?profile=$username"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val results = Gson().fromJson<User>(it, User::class.java)
                userLiveData.value = results
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