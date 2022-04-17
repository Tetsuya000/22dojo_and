package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapPathFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Section
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.collectWhenStarted
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.gone
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.visible
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbarError

@AndroidEntryPoint
class RoadmapPathFragment : Fragment(R.layout.roadmap_path_fragment) {

    private var _binding: RoadmapPathFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoadmapPathViewModel by viewModels()
    private lateinit var roadmapPathController: RoadmapPathController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapPathFragmentBinding.bind(view)
        setupRecyclerView()
        setupToolbar()
        observe()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun setupRecyclerView() {
        roadmapPathController = RoadmapPathController(object : RoadmapPathController.SelectListener {
            override fun onSelected(sections: List<Section>) {
                val action = RoadmapPathFragmentDirections.navigatePathToSection(sections.toTypedArray())
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            adapter = roadmapPathController.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observe() {
        observeFetchRoadmap()
        observeLoadState()
    }

    private fun observeFetchRoadmap() = viewModel.roadMap.collectWhenStarted(viewLifecycleOwner) { roadmap ->
        if (roadmap == null) return@collectWhenStarted
        roadmapPathController.setData(roadmap.paths, listOf())
    }

    private fun observeLoadState() = viewModel.loadState.collectWhenStarted(viewLifecycleOwner) { state ->
        when (state) {
            is LoadState.Nothing -> Unit
            is LoadState.Loading -> binding.progressBar.visible()
            is LoadState.Done -> binding.progressBar.gone()
            is LoadState.Error -> {
                binding.progressBar.gone()
                makeSnackbarError(requireContext(), binding.root, "エラー")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
