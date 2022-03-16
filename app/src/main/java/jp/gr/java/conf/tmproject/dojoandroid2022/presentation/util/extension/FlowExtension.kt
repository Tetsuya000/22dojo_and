package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectWhenStarted(
    lifecycleOwner: LifecycleOwner,
    observer: (T) -> Unit
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collectWhenStarted.collect {
                observer(it)
            }
        }
    }
}
