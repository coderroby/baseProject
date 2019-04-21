package com.myres.noban.mykotlinmvpproject.feature.nointernetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.myres.noban.mykotlinmvpproject.R


/**
 * Created by Rafiqul Hasan Rony on 1/29/19.
 * Audacity It Solution.
 */

class NoInternetDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenDialogTheme_Dark)
        this.isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //   AppCompatButton appCompatButton = view.findViewById(R.id.btn_ok);
        //   appCompatButton.setOnClickListener(v -> dismiss());
    }

    companion object {
        val TAG = NoInternetDialog::class.java.simpleName
    }

}
