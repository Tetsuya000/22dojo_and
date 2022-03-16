package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.itemRoadmap

class RoadmapNodeController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Node>, List<Int>>() {

    override fun buildModels(
        allNodeList: List<Node>,
        masterNodeIdList: List<Int>
    ) {
        allNodeList.forEach { node ->
            val isMaster = masterNodeIdList.contains(node.id)
            itemRoadmap {
                id(node.id)
                isMaster(isMaster)
                isEndNode(node.childNodes.isEmpty())
                title(node.title)
                onClickListener(
                    View.OnClickListener {
                        this@RoadmapNodeController.selectListener.onSelected(
                            node, node.childNodes
                        )
                    }
                )
            }
        }
    }

    interface SelectListener {
        fun onSelected(
            selectedNode: Node,
            childNodes: List<Node>
        )
    }
}
