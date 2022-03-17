package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMemoDialogViewModel @Inject constructor(
    private val memoRepository: MemoRepository) : ViewModel() {

    private val _isEditSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isEditSuccess: SharedFlow<Boolean> = _isEditSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    fun saveMemo(
        memo: Memo) = viewModelScope.launch {
        runCatching {
            memoRepository.saveMemo(memo)
        }.onSuccess {
            _isEditSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }
}
