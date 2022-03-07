package jp.gr.java.conf.tmproject.dojoandroid2022.ui.rodemap

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.RoadmapFragmentBinding

class RoadMapFragment : Fragment(R.layout.roadmap_fragment) {

    private var _binding: RoadmapFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = RoadmapFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
