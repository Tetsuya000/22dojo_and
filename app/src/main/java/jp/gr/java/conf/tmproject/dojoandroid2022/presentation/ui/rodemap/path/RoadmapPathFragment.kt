package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.path

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapPathFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Path

@AndroidEntryPoint
class RoadmapPathFragment : Fragment(R.layout.roadmap_path_fragment) {

    private var _binding: RoadmapPathFragmentBinding? = null
    private val binding get() = _binding!!
    private val pathViewModel: RoadmapPathViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapPathFragmentBinding.bind(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val headerCustomViewController = RoadmapPathController(object : RoadmapPathController.SelectListener {
            override fun onSelected(path: Path) {
                println(path.title)
                val action = RoadmapPathFragmentDirections.navigatePathToSection(path)
                findNavController().navigate(action)
            }
        })

        binding.recyclerView.apply {
            this.adapter = headerCustomViewController.adapter
            this.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        }

        val paths = pathViewModel.parseRodeMap().paths
        headerCustomViewController.setData(paths, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
