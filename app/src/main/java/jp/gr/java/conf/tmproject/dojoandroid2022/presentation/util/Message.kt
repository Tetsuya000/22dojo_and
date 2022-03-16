package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import jp.gr.java.conf.tmproject.dojoandroid2022.R

fun showToast(
    context: Context,
    message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun makeSnackbar(
    context: Context,
    view: View,
    message: String): Snackbar =
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .setTextColor(context.getColor(R.color.snackbar_text)).setBackgroundTint(context.getColor(R.color.snackbar_background))

fun makeSnackbarError(
    context: Context,
    view: View,
    message: String): Snackbar =
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .setTextColor(context.getColor(R.color.snackbar_error_text))
        .setBackgroundTint(context.getColor(R.color.snackbar_error_background))
