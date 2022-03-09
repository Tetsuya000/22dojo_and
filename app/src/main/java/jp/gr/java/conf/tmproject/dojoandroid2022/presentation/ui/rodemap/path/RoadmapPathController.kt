package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Path
import jp.gr.java.conf.tmproject.dojoandroid2022.itemPath

class RoadmapPathController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Path>, Boolean>() {

    override fun buildModels(
        paths: List<Path>,
        loadingMore: Boolean
    ) {
        paths.forEachIndexed { index, path ->
            itemPath {
                id(path.id)
                title(path.title)
                onClickListener(
                    View.OnClickListener {
                        this@RoadmapPathController.selectListener.onSelected(paths[index])
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(path: Path)
    }
}
