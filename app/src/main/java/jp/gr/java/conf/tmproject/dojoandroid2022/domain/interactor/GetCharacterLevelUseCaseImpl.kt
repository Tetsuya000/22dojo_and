package jp.gr.java.conf.tmproject.dojoandroid2022.domain.interactor

import javax.inject.Inject

class GetCharacterLevelUseCaseImpl @Inject constructor(
//    private val roadmapRepository: RoadmapRepository
) : GetCharacterLevelUseCase {

    override fun getCharacterLevel(masterNodeSize: Int): Int = (masterNodeSize / 2) + 1
}
