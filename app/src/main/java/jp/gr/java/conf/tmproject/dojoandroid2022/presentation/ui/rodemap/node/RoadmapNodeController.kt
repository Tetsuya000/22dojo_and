//package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node
//
//import android.view.View
//import com.airbnb.epoxy.Typed2EpoxyController
//import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
//import jp.gr.java.conf.tmproject.dojoandroid2022.itemNode
//
//class RoadmapNodeController(
//    private val selectListener: SelectListener) : Typed2EpoxyController<List<Node>, List<Node>>() {
//
//    override fun buildModels(
//        allNodeList: List<Node>,
//        masterNodeList: List<Node>) {
//        allNodeList.forEach { node ->
//
//            val isMaster = masterNodeList.map { it.id }.contains(node.id)
//            itemNode {
//                id(node.id)
//                isMaster(isMaster)
//                isEndNode(node.childNodes.isEmpty())
//                title(node.title)
//
//                onClickListener(View.OnClickListener {
//                    this@RoadmapNodeController.selectListener.onClick(
//                        node, node.childNodes)
//                })
//
//                onLongClickListener(View.OnLongClickListener {
//                    this@RoadmapNodeController.selectListener.onLongClick(node)
//                    return@OnLongClickListener true
//                })
//            }
//        }
//    }
//
//    interface SelectListener {
//        fun onClick(
//            selectedNode: Node,
//            childNodes: List<Node>)
//
//        fun onLongClick(selectedNode: Node)
//    }
//}
