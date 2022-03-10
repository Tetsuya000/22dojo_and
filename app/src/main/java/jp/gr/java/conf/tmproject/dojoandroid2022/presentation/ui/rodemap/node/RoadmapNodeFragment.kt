package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapNodeFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node

@AndroidEntryPoint
class RoadmapNodeFragment : Fragment(R.layout.roadmap_node_fragment) {

    private var _binding: RoadmapNodeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapNodeViewModel by viewModels()
    private val navArgs by navArgs<RoadmapNodeFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapNodeFragmentBinding.bind(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val roadmapNodeController = RoadmapNodeController(object : RoadmapNodeController.SelectListener {
            override fun onSelected(
                selectedNode: Node,
                childNodes: List<Node>
            ) {

                if (childNodes.isEmpty()) return saveNode(selectedNode)
                val action = RoadmapNodeFragmentDirections.navigateNodeToChildNodes(childNodes.toTypedArray())
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapNodeController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val nodes = navArgs.nodes?.toList()
        roadmapNodeController.setData(nodes, true)
    }

    private fun saveNode(selectedNode: Node) {
        val isMastery = viewModel.checkNodeMastery(selectedNode.id)
        if (isMastery) {
            viewModel.deleteNode(selectedNode.id)
        } else {
            viewModel.saveNode(selectedNode.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
