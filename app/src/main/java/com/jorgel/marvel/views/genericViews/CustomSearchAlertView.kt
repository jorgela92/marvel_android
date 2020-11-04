package com.jorgel.marvel.views.genericViews

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.jorgel.marvel.R
import kotlinx.android.synthetic.main.custom_dialog_search.*

class CustomSearchAlertView (
    activity: Activity?,
    private val title: String?,
    private val textButtonLeft: String?,
    private val textButtonRight: String?,
    private val delegate: CustomDialogCallback?
) :
    AlertDialog(activity), View.OnClickListener {
    interface CustomDialogCallback {
        fun onClickCancelButton()
        fun onClickSearchButton(stringSearch: String?)
    }

    override fun show() {
        super.show()
        setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureUI()
    }

    private fun configureUI() {
        setContentView(R.layout.custom_dialog_search)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        buttonLeftAlert.setOnClickListener(this)
        buttonRigthAlert.setOnClickListener(this)

        if (title == null || "" == title) {
            textTitleDialogSearch.visibility = View.GONE
        } else {
            textTitleDialogSearch.text = title
        }
        if (textButtonLeft == null || "" == textButtonLeft) {
            buttonLeftAlert.visibility = View.GONE
            guidelineButtonAlert.setGuidelinePercent(0.0.toFloat())
        } else {
            buttonLeftAlert.text = textButtonLeft
        }
        if (textButtonRight == null || "" == textButtonRight) {
            buttonRigthAlert.visibility = View.GONE
            guidelineButtonAlert.setGuidelinePercent(1.0.toFloat())
        } else {
            buttonRigthAlert.text = textButtonRight
        }
        if ((textButtonLeft == null || "" == textButtonLeft) && (textButtonRight == null || "" == textButtonRight)) {
            buttonRigthAlert.visibility = View.GONE
            buttonLeftAlert.visibility = View.VISIBLE
            guidelineButtonAlert.setGuidelinePercent(1.0.toFloat())
            buttonLeftAlert.text = context.getText(R.string.accept)
        }
        //window!!.attributes.windowAnimations = R.style.SlidingDialogAnimation
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonLeftAlert -> {
                if (delegate != null) {
                    delegate.onClickCancelButton()
                    return
                }
                hide()
            }
            R.id.buttonRigthAlert -> {
                if (delegate != null) {
                    delegate.onClickSearchButton(editTextSearch.text.toString())
                    return
                }
                hide()
            }
        }
    }
}