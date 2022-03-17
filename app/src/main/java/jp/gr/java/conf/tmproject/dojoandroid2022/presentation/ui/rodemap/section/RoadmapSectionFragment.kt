package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapSectionFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.warning.DeleteNodeDialogFragment
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbar

@AndroidEntryPoint
class RoadmapSectionFragment : Fragment(R.layout.roadmap_section_fragment) {

    private var _binding: RoadmapSectionFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapSectionViewModel by hiltNavGraphViewModels(R.id.section)
    private val navArgs by navArgs<RoadmapSectionFragmentArgs>()
    private lateinit var roadmapSectionController: RoadmapSectionController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapSectionFragmentBinding.bind(view)
        setUpRecyclerView()
        setUpToolbar()
        observe()
    }

    private fun setUpToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun setUpRecyclerView() {
        roadmapSectionController = RoadmapSectionController(object : RoadmapSectionController.SelectListener {
            override fun onClick(node: Node) {
                // ChildNodesが存在しなければ、末端であると判定して、習得状態に応じて保存、または、メモ画面に遷移する
                saveNodeOrNavigateDetailMemo(node)

                // ChildNodesが存在する場合、ChildNodesの表示画面に遷移する
//                val action = RoadmapNodeFragmentDirections.navigateNodeToChildNodes(
//                    childNodes.toTypedArray(), "ChildNode")
//                findNavController().navigate(action)
            }

            override fun onLongClick(selectedNode: Node) {
                showWarningDeleteNodeDialog(selectedNode)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapSectionController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun saveNodeOrNavigateDetailMemo(selectedNode: Node) {
        val isMaster = viewModel.isMaster(selectedNode.id)
        if (isMaster) {
            val action = RoadmapSectionFragmentDirections.navigateNodeToDetailMemo(selectedNode.id, selectedNode.title, "")
            findNavController().navigate(action)
        }
        else {
            viewModel.saveNode(selectedNode)
        }
    }

    private fun showWarningDeleteNodeDialog(node: Node) {
        val dialogFragment = DeleteNodeDialogFragment()
        val args = Bundle()
        args.putParcelable("node", node)
        dialogFragment.arguments = args
        dialogFragment.show(parentFragmentManager, "DeleteNodeDialog")
    }

    private fun observe() {
        observeMasterNode()
        observeUpdateLevel()
    }

    private fun observeMasterNode() {
        val sections = navArgs.sections.toList()
        viewModel.masterNodeList.collectWhenStarted(viewLifecycleOwner) { masterNodeList ->
            roadmapSectionController.setData(sections, masterNodeList)
        }
    }

    private fun observeUpdateLevel() {
        viewModel.isLevelUp.collectWhenStarted(viewLifecycleOwner) { isLevelUp ->
            if (isLevelUp == null) return@collectWhenStarted

            if (isLevelUp) {
                makeSnackbar(requireContext(), binding.snackbarSpace, getString(R.string.snackbar_text_level_up)).show()
            }
            else {
                makeSnackbar(requireContext(), binding.snackbarSpace, getString(R.string.snackbar_text_level_down)).show()
            }

            viewModel.clearLevel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
