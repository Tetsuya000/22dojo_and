package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.node

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapSectionFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.collectWhenStarted

@AndroidEntryPoint
class RoadmapNodeFragment : Fragment(R.layout.roadmap_section_fragment) {

    private var _binding: RoadmapSectionFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapNodeViewModel by hiltNavGraphViewModels(R.id.section)
    private val navArgs by navArgs<RoadmapNodeFragmentArgs>()
    private lateinit var roadmapNodeController: RoadmapNodeController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapSectionFragmentBinding.bind(view)
        setupRecyclerView()
        setupToolbar()
        observe()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun setupRecyclerView() {
        roadmapNodeController = RoadmapNodeController(object : RoadmapNodeController.SelectListener {
            override fun onClick(node: Node) {
                saveNodeOrNavigateDetailMemo(node)
            }

            override fun onLongClick(selectedNode: Node) {
                val action = RoadmapNodeFragmentDirections.navigateDeleteNodeDialog(selectedNode)
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapNodeController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun saveNodeOrNavigateDetailMemo(selectedNode: Node) {
        val isMaster = viewModel.isMaster(selectedNode.id)
        if (isMaster) {
            val action = RoadmapNodeFragmentDirections.navigateNodeToDetailMemo(selectedNode.id, selectedNode.title, "")
            findNavController().navigate(action)
        } else {
            viewModel.saveNode(selectedNode)
        }
    }

    private fun observe() {
        observeMasterNode()
        observeUpdateLevel()
    }

    private fun observeMasterNode() {
        val sections = navArgs.sections.toList()
        viewModel.masterNodeList.collectWhenStarted(viewLifecycleOwner) { masterNodeList ->
            roadmapNodeController.setData(sections, masterNodeList)
        }
    }

    private fun observeUpdateLevel() {
        viewModel.isLevelUp.collectWhenStarted(viewLifecycleOwner) { isLevelUp ->
            if (isLevelUp == null) return@collectWhenStarted

            if (isLevelUp) {
                Snackbar.make(binding.snackbarSpace, getString(R.string.snackbar_text_level_up), Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .setTextColor(requireContext().getColor(R.color.snackbar_text_level_change))
                    .setBackgroundTint(requireContext().getColor(R.color.snackbar_background_level_up)).show()
            } else {
                Snackbar
                    .make(binding.snackbarSpace, getString(R.string.snackbar_text_level_down), Snackbar.LENGTH_SHORT)
                    .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                    .setTextColor(requireContext().getColor(R.color.snackbar_text_level_change))
                    .setBackgroundTint(requireContext().getColor(R.color.snackbar_background_level_down)).show()
            }
            viewModel.clearLevel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
