package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section
import jp.gr.java.conf.tmproject.dojoandroid2022.itemHeaderNode
import jp.gr.java.conf.tmproject.dojoandroid2022.itemHeaderSection
import jp.gr.java.conf.tmproject.dojoandroid2022.itemNode

class RoadmapSectionController(
    private val selectListener: SelectListener) : Typed2EpoxyController<List<Section>, List<Node>>() {

    override fun buildModels(
        sections: List<Section>,
        masterNodeList: List<Node>) {

        sections.forEach { section ->

            itemHeaderSection {
                id(section.id)
                title("ー${section.title}ー")
            }

            section.nodes.forEach { node ->
                if (node.childNodes.isNotEmpty()) {
                    itemHeaderNode {
                        id(node.id)
                        title(node.title)
                    }

                    node.childNodes.forEach { childNode ->
                        val isMaster = masterNodeList.map { it.id }.contains(childNode.id)

                        itemNode {
                            id(childNode.id)
                            isMaster(isMaster)
                            title(childNode.title)
                            onClickListener(View.OnClickListener {
                                this@RoadmapSectionController.selectListener.onClick(childNode)
                            })
                            onLongClickListener(View.OnLongClickListener {
                                this@RoadmapSectionController.selectListener.onLongClick(childNode)
                                return@OnLongClickListener true
                            })
                        }
                    }
                }
                else {
                    val isMaster = masterNodeList.map { it.id }.contains(node.id)
                    itemNode {
                        id(node.id)
                        isMaster(isMaster)
                        title(node.title)
                        onClickListener(View.OnClickListener {
                            this@RoadmapSectionController.selectListener.onClick(node)
                        })
                        onLongClickListener(View.OnLongClickListener {
                            this@RoadmapSectionController.selectListener.onLongClick(node)
                            return@OnLongClickListener true
                        })
                    }
                }
            }
        }
    }

    interface SelectListener {
        fun onClick(node: Node)
        fun onLongClick(selectedNode: Node)
    }
}
