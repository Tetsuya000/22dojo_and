package jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor

import jp.gr.java.conf.tmproject.dojoandroid2022.domain.repository.RoadmapRepository
import javax.inject.Inject

class GetCharacterLevelUseCaseImpl @Inject constructor(
    private val roadmapRepository: RoadmapRepository
) : GetCharacterLevelUseCase {

    override fun getCharacterLevel(): Int {
//        val nodes = roadmapRepository.loadAllNode()
//        return (nodes .size / 2) + 1
        return 1
    }
}
