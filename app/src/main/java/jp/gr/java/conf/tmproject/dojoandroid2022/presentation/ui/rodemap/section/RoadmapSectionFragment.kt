package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapSectionFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Node

@AndroidEntryPoint
class RoadmapSectionFragment : Fragment(R.layout.roadmap_section_fragment) {

    private var _binding: RoadmapSectionFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapSectionViewModel by viewModels()
    private val navArgs by navArgs<RoadmapSectionFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapSectionFragmentBinding.bind(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val roadmapSectionController = RoadmapSectionController(object : RoadmapSectionController.SelectListener {
            override fun onSelected(nodes: List<Node>) {
                val action = RoadmapSectionFragmentDirections.navigateSectionToNode(nodes.toTypedArray())
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapSectionController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val sections = navArgs.sections.toList()
        roadmapSectionController.setData(sections)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
