package com.jorgel.marvel.application

import android.app.Application
import android.content.Context

class MarvelApplication: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MarvelApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}