package com.jorgel.marvel.coreServices

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jorgel.marvel.application.MarvelApplication
import com.jorgel.marvel.models.ErrorModel
import com.jorgel.marvel.models.ResultObject
import org.json.JSONObject

class ConnectionManager {

    private val manager = Volley.newRequestQueue(MarvelApplication.applicationContext())

    companion object {
        fun newInstance() = ConnectionManager()
    }

    fun request(method: Int, url: String): MutableLiveData<ResultObject> {
        val observerResult: MutableLiveData<ResultObject> = MutableLiveData()
        val request = StringRequest(method, url, {
            observerResult.value = ResultObject(JSONObject(it), null)
        }, {
            observerResult.value = ResultObject(null, ErrorModel(it.hashCode(), it.message.toString()))
        })
        manager.add(request)
        return observerResult
    }
}