package com.jorgel.marvel.coreServices.servicesHandler

import java.security.MessageDigest
import java.util.*

open class BaseServiceHandler {
    private val baseUrlDEV = "https://gateway.marvel.com/v1/public/"
    private val apikey = "8b32f2fc7c43f1f44399784d59fb3e4b"
    private val privateApikey = "20000c5ccd6c0ea2823e04977bfd9304f91dab9d"

    fun getBaseUrl(path: String): String {
        return baseUrlDEV + path
    }

    fun getParameters(nameStartsWith: String?, offset: Int?, limit: Int?): String {
        val ts = getTS()
        var string = "?apikey=" + apikey + "&ts=" + ts + "&hash=" + getHash(ts)
        if (nameStartsWith != null) {
            string = "$string&nameStartsWith=$nameStartsWith"
        }
        if (offset != null) {
            string = "$string&offset=$offset"
        }
        if (limit != null) {
            string = "$string&limit=$limit"
        }
        return  string
    }


    private fun getTS(): String {
        return "" + Date().time * 1000000
    }

    private fun getHash(ts: String): String {
        return md5(ts + privateApikey + apikey)
    }

    private fun md5(string: String): String {
        val bytes = MessageDigest.getInstance("MD5").digest(string.toByteArray())
        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }
}