package com.jorgel.marvel.views.fragments.detailCharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorgel.marvel.coreServices.ResultResponse
import com.jorgel.marvel.coreServices.servicesHandler.CharactersServiceHandler
import com.jorgel.marvel.models.CharactersModel
import com.jorgel.marvel.models.ErrorModel

class DetailCharacterViewModel : ViewModel() {
    private val charactersObserver = MutableLiveData<CharactersModel>()
    private val errorObserver = MutableLiveData<ErrorModel>()

    fun getDetailData(id: Int) {
        CharactersServiceHandler()
            .getDetailCharacters(
                id,
                object : ResultResponse<CharactersModel?> {
                    override fun onSuccess(data: CharactersModel?) {
                        charactersObserver.value = data
                    }
                    override fun onError(description: ErrorModel?) {
                        errorObserver.value = description
                    }
                }
            )
    }

    fun getCharacterDetail(): LiveData<CharactersModel> {
        return charactersObserver
    }

    fun getCharacterDetailError(): LiveData<ErrorModel> {
        return errorObserver
    }
}