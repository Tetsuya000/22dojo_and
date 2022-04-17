package jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.constants.CharacterConstants.CHANGE_LEVEL_NUMBER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterLevelUseCaseImpl @Inject constructor(
    private val roadmapRepository: RoadmapRepository) : GetCharacterLevelUseCase {

    override fun getCharacterLevel(): Flow<Int> {
        return roadmapRepository.loadAllNode()
            .map { masterNode ->
                (masterNode.size / CHANGE_LEVEL_NUMBER) + 1
            }
    }
}
