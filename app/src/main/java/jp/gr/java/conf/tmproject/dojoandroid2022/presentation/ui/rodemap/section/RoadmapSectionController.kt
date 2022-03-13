package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section
import jp.gr.java.conf.tmproject.dojoandroid2022.itemRoadmap

class RoadmapSectionController(
    private val selectListener: SelectListener) : TypedEpoxyController<List<Section>>() {

    override fun buildModels(
        sections: List<Section>) {

        sections.forEach { section ->
            itemRoadmap {
                id(section.id)
                title(section.title)
                onClickListener(View.OnClickListener {
                    this@RoadmapSectionController.selectListener.onSelected(section.nodes)
                })
            }
        }
    }

    interface SelectListener {
        fun onSelected(nodes: List<Node>)
    }
}
