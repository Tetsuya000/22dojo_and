package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Path
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section
import jp.gr.java.conf.tmproject.dojoandroid2022.itemSection

class RoadmapPathController(
    private val selectListener: SelectListener) : Typed2EpoxyController<List<Path>,List<Int>>() {

    override fun buildModels(
        paths: List<Path>,
        completePathList: List<Int>) {

        paths.forEach { path ->
            itemSection {
                id(path.id)
                title(path.title)
                onClickListener(View.OnClickListener {
                    this@RoadmapPathController.selectListener.onSelected(path.sections)
                })
            }
        }
    }

    interface SelectListener {
        fun onSelected(sections: List<Section>)
    }
}
