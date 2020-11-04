package com.jorgel.marvel.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.jorgel.marvel.R
import com.jorgel.marvel.views.fragments.detailCharacter.DetailCharacterFragment
import com.jorgel.marvel.views.fragments.detailCharacter.DetailCharacterFragmentCallback
import com.jorgel.marvel.views.fragments.listCharacters.ListCharactersFragment
import com.jorgel.marvel.views.fragments.webView.WebViewFragment
import kotlinx.android.synthetic.main.activity_main.*


interface CharacterClickCallbacks {
    fun onCharacterSelectedClick(title: String, characterSelected: Int)
}

class MainActivity : AppCompatActivity(), CharacterClickCallbacks, DetailCharacterFragmentCallback {

    private var title: String = ""
    private var characterSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureUi()
    }

    private fun configureUi() {
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        loadListCharactersFragment()
    }

    private fun loadListCharactersFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListCharactersFragment.newInstance(this))
            .commitNow()
            imageToolbar.visibility = View.VISIBLE
            titleToolbar.visibility = View.GONE
            val params: AppBarLayout.LayoutParams = mainToolbar.layoutParams as AppBarLayout.LayoutParams
            params.scrollFlags = 0
            mainToolbar.layoutParams = params
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    private fun loadDetailCharacterFragment(title: String, characterSelected: Int) {
        this.title = title
        this.characterSelected = characterSelected
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailCharacterFragment.newInstance(characterSelected, this))
            .commitNow()
        configureToolbarTitle(title)
        mainToolbar.setNavigationOnClickListener {
            this.title = ""
            this.characterSelected = 0
            loadListCharactersFragment()
        }
    }

    private fun loadWebViewFragment(title: String, url: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, WebViewFragment.newInstance(url))
            .commitNow()
        mainToolbar.setNavigationOnClickListener { loadDetailCharacterFragment(this.title, this.characterSelected) }
        configureToolbarTitle(title)
    }

    private fun configureToolbarTitle(title: String) {
            imageToolbar.visibility = View.GONE
            titleToolbar.visibility = View.VISIBLE
            titleToolbar.text = title
            val params: AppBarLayout.LayoutParams = mainToolbar.layoutParams as AppBarLayout.LayoutParams
            params.scrollFlags = 0
            mainToolbar.layoutParams = params
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCharacterSelectedClick(title: String, characterSelected: Int) {
        loadDetailCharacterFragment(title, characterSelected)
    }

    override fun backList() {
        loadListCharactersFragment()
    }

    override fun openLink(title: String, url: String) {
        loadWebViewFragment(title, url)
    }
}