package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.tab

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapTabFragmentBinding

@AndroidEntryPoint
class RoadmapTabFragment : Fragment(R.layout.roadmap_tab_fragment) {
    private var _binding: RoadmapTabFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapTabFragmentBinding.bind(view)
    }
}
