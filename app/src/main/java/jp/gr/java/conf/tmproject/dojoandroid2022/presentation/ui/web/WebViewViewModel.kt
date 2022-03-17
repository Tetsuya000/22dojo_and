package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.web

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor() : ViewModel() {

    private val _loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Nothing)
    val loadState: MutableStateFlow<LoadState> = _loadState

    fun changeLoadState(loadState: LoadState) {
        _loadState.value = loadState
    }
}
