package com.jorgel.marvel.views.fragments.detailCharacter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorgel.marvel.coreServices.servicesHandler.CharactersServiceHandler
import com.jorgel.marvel.models.CharactersModel
import com.jorgel.marvel.models.ErrorModel

class DetailCharacterViewModel : ViewModel() {
    private val charactersObserver = MutableLiveData<CharactersModel>()
    private val errorObserver = MutableLiveData<ErrorModel>()

    fun getDetailData(owner: LifecycleOwner, id: Int) {
        CharactersServiceHandler().getDetailCharacters(owner, id).observe(owner, {
            when(null) {
                it.error -> {
                    charactersObserver.value = it.obj as CharactersModel
                }
                it.obj -> {
                    errorObserver.value = it.error
                }
            }
        })
    }

    fun getCharacterDetail(): LiveData<CharactersModel> {
        return charactersObserver
    }

    fun getCharacterDetailError(): LiveData<ErrorModel> {
        return errorObserver
    }
}