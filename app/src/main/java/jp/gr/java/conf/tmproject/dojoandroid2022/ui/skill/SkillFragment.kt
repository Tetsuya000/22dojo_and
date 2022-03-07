package jp.gr.java.conf.tmproject.dojoandroid2022.ui.skill

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.SkillFragmentBinding

class SkillFragment : Fragment(R.layout.skill_fragment) {

    private var _binding: SkillFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SkillFragmentBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}