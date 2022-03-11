package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.itemRoadmap

class RoadmapNodeController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Node>, Boolean>() {

    override fun buildModels(
        nodes: List<Node>,
        loadingMore: Boolean
    ) {

        nodes.forEach { node ->
            itemRoadmap {

                id(node.id)
                title(node.title)
                onClickListener(
                    View.OnClickListener {
                        this@RoadmapNodeController.selectListener.onSelected(node, node.childNodes ?: emptyList())
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(selectedNode: Node, childNodes: List<Node>)
    }
}
