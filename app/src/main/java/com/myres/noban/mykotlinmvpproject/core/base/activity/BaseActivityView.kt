package com.myres.noban.mykotlinmvpproject.core.base.activity

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */


interface BaseActivityView {
    val context: Context

    val activity: AppCompatActivity

    fun getResString(id: Int): String

    fun showLoader()

    fun hideLoader()

    fun hideKeyboard(view: View)

    fun showToast(text: String)
}
