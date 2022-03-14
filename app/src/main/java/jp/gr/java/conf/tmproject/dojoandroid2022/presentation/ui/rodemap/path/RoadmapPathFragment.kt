package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapPathFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section

@AndroidEntryPoint
class RoadmapPathFragment : Fragment(R.layout.roadmap_path_fragment) {

    private var _binding: RoadmapPathFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapPathViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapPathFragmentBinding.bind(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val roadmapPathController = RoadmapPathController(object : RoadmapPathController.SelectListener {
            override fun onSelected(sections: List<Section>) {
                val action = RoadmapPathFragmentDirections.navigatePathToSection(sections.toTypedArray())
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = roadmapPathController.adapter
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val paths = viewModel.parseRodeMap().paths
        roadmapPathController.setData(paths)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
