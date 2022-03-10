package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Path
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section
import jp.gr.java.conf.tmproject.dojoandroid2022.itemRoadmap

class RoadmapPathController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Path>, Boolean>() {

    override fun buildModels(
        paths: List<Path>,
        loadingMore: Boolean
    ) {

        paths.forEach { path ->
            itemRoadmap {
                id(path.id)
                title(path.title)
                onClickListener(
                    View.OnClickListener {
                        this@RoadmapPathController.selectListener.onSelected(path.sections)
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(sections: List<Section>)
    }
}
