package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
