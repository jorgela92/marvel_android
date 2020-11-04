package com.jorgel.marvel.coreServices.servicesHandler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.observe
import com.android.volley.Request
import com.google.gson.Gson
import com.jorgel.marvel.coreServices.ConnectionManager
import com.jorgel.marvel.models.CharactersModel
import com.jorgel.marvel.models.ErrorModel
import com.jorgel.marvel.models.ResultObject
import org.json.JSONObject

class CharactersServiceHandler: BaseServiceHandler() {
    private val baseServiceUrl = "characters"

    fun getCharacters(owner: LifecycleOwner, nameStartsWith: String? = null, offset: Int? = null, limit: Int? = null): MutableLiveData<ResultObject> {
        val observerResult: MutableLiveData<ResultObject> = MutableLiveData()
        ConnectionManager.newInstance().request(
                Request.Method.GET,
                getUrl(nameStartsWith = nameStartsWith, offset = offset, limit = limit)
        ).observe(owner, {
            when(null) {
                it.error -> {
                    if (fromJson((it.obj as JSONObject).toString()) != null) {
                        observerResult.value = ResultObject(fromJson((it.obj as JSONObject).toString()), null)
                    } else {
                        observerResult.value = ResultObject(null, fromErrorJson((it.obj as JSONObject).toString()))
                    }
                }
                it.obj -> {
                    observerResult.value = ResultObject(null, it.error)
                }
            }
        })
        return observerResult
    }

    fun getDetailCharacters(owner: LifecycleOwner, characterId: Int): MutableLiveData<ResultObject> {
        val observerResult: MutableLiveData<ResultObject> = MutableLiveData()
        ConnectionManager.newInstance().request(
            Request.Method.GET,
            getUrl(characterId= characterId)
        ).observe(owner, {
            when(null) {
                it.error -> {
                    if (fromJson((it.obj as JSONObject).toString()) != null) {
                        observerResult.value = ResultObject(fromJson((it.obj as JSONObject).toString()), null)
                    } else {
                        observerResult.value = ResultObject(null, fromErrorJson((it.obj as JSONObject).toString()))
                    }
                }
                it.obj -> {
                    observerResult.value = ResultObject(null, it.error)
                }
            }
        })
        return observerResult
    }

    private fun getUrl(nameStartsWith: String? = null, offset: Int? = null, limit: Int? = null, characterId: Int? = null): String {
        return getBaseUrl(baseServiceUrl + getPathURL(characterId) + getParameters(nameStartsWith, offset, limit))
    }

    private fun getPathURL(characterId: Int?): String {
        if (characterId != null) {
            return  "/$characterId"
        }
        return ""
    }

    private fun fromJson(jsonObject: String): CharactersModel? {
        return Gson().fromJson(jsonObject, CharactersModel::class.java)
    }

    private fun fromErrorJson(jsonObject: String): ErrorModel? {
        return Gson().fromJson(jsonObject, ErrorModel::class.java)
    }
}