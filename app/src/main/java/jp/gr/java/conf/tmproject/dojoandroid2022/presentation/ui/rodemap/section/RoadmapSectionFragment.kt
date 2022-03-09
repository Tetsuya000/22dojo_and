package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapSectionFragmentBinding

@AndroidEntryPoint
class RoadmapSectionFragment : Fragment(R.layout.roadmap_section_fragment) {

    private var _binding: RoadmapSectionFragmentBinding? = null
    private val binding get() = _binding!!
    private val sectionViewModel: RoadmapSectionViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapSectionFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
