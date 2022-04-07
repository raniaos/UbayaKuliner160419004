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
import com.ubaya.ubayakuliner160419004.model.Menu

class MenuDetailViewModel (application: Application) : AndroidViewModel(application) {
    val menuDetailLiveData = MutableLiveData<Menu>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refreshDetail(id: String) {
        queue = Volley.newRequestQueue(getApplication())
        var id_res = Integer.parseInt(id)
        val url = "http://192.168.0.195/anmp/uts/get_menu.php?idmenu=$id_res"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val results = Gson().fromJson<Menu>(it, Menu::class.java)
                menuDetailLiveData.value = results
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