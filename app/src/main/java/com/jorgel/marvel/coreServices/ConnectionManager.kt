package com.jorgel.marvel.coreServices

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jorgel.marvel.application.MarvelApplication
import com.jorgel.marvel.models.ErrorModel
import org.json.JSONObject

class ConnectionManager {

    private val manager = Volley.newRequestQueue(MarvelApplication.applicationContext())

    companion object {
        fun newInstance() = ConnectionManager()
    }

    fun request(method: Int, url: String, delegate: ResultResponse<JSONObject?>) {
        val request = StringRequest(method, url, {
            delegate.onSuccess(JSONObject(it))
        }, {
            delegate.onError(ErrorModel(
                it.hashCode(),
                it.message.toString()
            ))
        })
        manager.add(request)
    }
}