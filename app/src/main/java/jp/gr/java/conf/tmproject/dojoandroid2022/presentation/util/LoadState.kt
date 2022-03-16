package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util

sealed class LoadState {
    object Nothing : LoadState()
    object Loading : LoadState()
    object Done : LoadState()
    object Error : LoadState()
}
