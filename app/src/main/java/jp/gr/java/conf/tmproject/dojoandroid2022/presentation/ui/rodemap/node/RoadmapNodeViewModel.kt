package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor.GetCharacterLevelUseCase
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.MemoRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadmapNodeViewModel @Inject constructor(
    private val roadmapRepository: RoadmapRepository,
    private val memoRepository: MemoRepository,
    private val getCharacterLevelUseCase: GetCharacterLevelUseCase
) : ViewModel() {

    private val _masterNodeList: MutableStateFlow<List<Node>> = MutableStateFlow(emptyList())
    val masterNodeList: StateFlow<List<Node>> = _masterNodeList

    private val _isLevelUp: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLevelUp: StateFlow<Boolean?> = _isLevelUp

    private val oldCharacterLevel: MutableStateFlow<Int> = MutableStateFlow(-1)

    private val _isSaveSuccess: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isSaveSuccess: SharedFlow<Boolean> = _isSaveSuccess

    private val _isError: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isError: SharedFlow<Boolean> = _isError

    /**
     * 「選択したノード」が「習得済みノードリスト」に存在するか
     */
    fun isMaster(selectedNodeId: Int): Boolean = masterNodeList.value.map { it.id }.contains(selectedNodeId)

    private fun loadAllNode() = viewModelScope.launch {
        roadmapRepository.loadAllNode().collect { list ->
            _masterNodeList.value = list
        }
    }

    fun saveNode(
        node: Node?
    ) = viewModelScope.launch {
        if (node == null) return@launch

        runCatching {
            val memo = Memo(node.id, node.title, "")
            roadmapRepository.saveNode(node)
            memoRepository.saveMemo(memo)
        }.onSuccess {
            _isSaveSuccess.emit(true)
        }.onFailure {
            _isError.emit(true)
        }
    }

    private fun updateCharacterLevel() = viewModelScope.launch {
        getCharacterLevelUseCase.getCharacterLevel().collect { latestCharacterLevel ->
            val isInitialize = oldCharacterLevel.value != -1
            val noChangeLevel = oldCharacterLevel.value == latestCharacterLevel
            if (!isInitialize || noChangeLevel) {
                oldCharacterLevel.value = latestCharacterLevel
                return@collect
            }

            _isLevelUp.emit(oldCharacterLevel.value < latestCharacterLevel)
            oldCharacterLevel.value = latestCharacterLevel
        }
    }

    fun clearLevel() {
        _isLevelUp.value = null
    }

    init {
        loadAllNode()
        updateCharacterLevel()
    }
}
