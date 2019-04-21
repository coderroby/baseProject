package com.myres.noban.mykotlinmvpproject.core.base.fragment

import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

interface BaseFragmentView {
  //  val context: Context

    val fragment: Fragment

    fun getResString(@StringRes id: Int): String

    fun getDimension(@DimenRes id: Int): Float

    fun showLoader()

    fun hideLoader()

    fun showToast(text: String)

    fun showSnackBar(message: String, parentLayout: View, snackBarType: Int)

}
