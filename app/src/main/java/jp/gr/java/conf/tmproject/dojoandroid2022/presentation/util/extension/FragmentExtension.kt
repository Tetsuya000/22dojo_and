package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.showKeyboard(targetView: View) {
    targetView.requestFocus()
    val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(targetView, InputMethodManager.SHOW_IMPLICIT)
}

// fun Fragment.showKeyboard(targetView: View) {
//    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
//    targetView.postDelayed( {
//        targetView.requestFocus()
//        imm?.showSoftInput(targetView, 0)
//    }, 100)
// }

fun Fragment.hideKeyboard() {
    val manager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(requireView().windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}
