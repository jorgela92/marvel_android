package com.jorgel.marvel.views.fragments.detailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jorgel.marvel.R
import com.jorgel.marvel.views.fragments.detailCharacter.adapters.DetailCharacterAdapter
import com.jorgel.marvel.views.fragments.detailCharacter.adapters.DetailCharacterAdapterCallback
import com.jorgel.marvel.views.genericViews.CustomAlertView
import com.jorgel.marvel.views.genericViews.CustomLoading
import kotlinx.android.synthetic.main.detail_characters_fragment.*

interface DetailCharacterFragmentCallback {
    fun openLink(title: String, url: String)
    fun backList()
}

class DetailCharacterFragment: Fragment(), DetailCharacterAdapterCallback, CustomAlertView.CustomDialogCallback {

    companion object {
        private const val characterSelectedId = "characterSelected"
        private var delegate: DetailCharacterFragmentCallback? = null
        fun newInstance(id: Int, delegate: DetailCharacterFragmentCallback): DetailCharacterFragment {
            this.delegate = delegate
            val fragment = DetailCharacterFragment()
            val args = Bundle()
            args.putInt(characterSelectedId, id)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: DetailCharacterViewModel
    private var alertView: CustomAlertView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureUI()
    }

    private fun configureUI() {
        viewModel = ViewModelProvider(this).get(DetailCharacterViewModel::class.java)
        arguments?.getInt(characterSelectedId)?.let { viewModel.getDetailData(this, it) }
        detailCharacterRecyclerView.layoutManager = LinearLayoutManager(context)
        viewModel.getCharacterDetail().observe(this, {
            detailCharacterRecyclerView.adapter = context?.let { context -> DetailCharacterAdapter(context, it.data, this) }
            CustomLoading.getInstance(activity).hide()
        })
        viewModel.getCharacterDetailError().observe(this, {
            alertView = CustomAlertView(activity, "", context?.getString(R.string.error), it.message, null, context?.getString(R.string.accept), this, 0, false)
            alertView?.show()
        })
    }

    override fun openLink(title: String, url: String) {
        delegate?.openLink(title, url)
    }

    override fun onClickLeftButton(tag: Int) {}

    override fun onClickRightButton(tag: Int) {
        alertView?.hide()
        delegate?.backList()
    }
}