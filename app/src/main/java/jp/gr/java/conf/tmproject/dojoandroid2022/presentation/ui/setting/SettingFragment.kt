package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.SettingFragmentBinding

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.setting_fragment) {

    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SettingFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadCharacterName()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
