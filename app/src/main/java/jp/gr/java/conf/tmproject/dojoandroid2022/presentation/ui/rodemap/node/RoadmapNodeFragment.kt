package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapNodeFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        observeUpdateLevel()
    }

    private fun setUpRecyclerView() {
        val roadmapNodeController = RoadmapNodeController(object : RoadmapNodeController.SelectListener {
            override fun onSelected(
                selectedNode: Node,
                childNodes: List<Node>
            ) {
                // ChildNodesが存在しなければ、末端であると判定して、習得状態に応じて編集画面か詳細画面に遷移する
                if (childNodes.isEmpty()) return navigateNodeEditOrDetail(selectedNode)

                // ChildNodesが存在する場合、ChildNodesの表示画面に遷移する
                val action = RoadmapNodeFragmentDirections.navigateNodeToChildNodes(childNodes.toTypedArray())
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapNodeController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val screenNodeList = navArgs.nodes?.toList()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.masterNodeList.collect { masterNodeList ->
                    roadmapNodeController.setData(screenNodeList, masterNodeList.map { it.id })
                }
            }
        }
    }

    private fun navigateNodeEditOrDetail(selectedNode: Node) {
        val isMaster = viewModel.isMaster(selectedNode.id)

        if (isMaster) {
            val action = RoadmapNodeFragmentDirections.navigateChildNodesToDetail(selectedNode)
            findNavController().navigate(action)
        } else {
            val action = RoadmapNodeFragmentDirections.navigateChildNodesToEdit(selectedNode)
            findNavController().navigate(action)
        }
    }

    private fun observeUpdateLevel() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.currentCharacterLevel.collect { currentCharacterLevel ->
                if (viewModel.isLevelInitialize) {
                    val oldCharacterLevel = viewModel.oldCharacterLevel.value
                    val action = RoadmapNodeFragmentDirections.navigateChildNodesToUpdateLevelDialog(
                        oldCharacterLevel, currentCharacterLevel
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
