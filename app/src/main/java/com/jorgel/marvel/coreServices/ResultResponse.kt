package com.jorgel.marvel.coreServices

import com.jorgel.marvel.models.ErrorModel

interface ResultResponse<T> {
    fun onSuccess(data: T)
    fun onError(description: ErrorModel?)
}