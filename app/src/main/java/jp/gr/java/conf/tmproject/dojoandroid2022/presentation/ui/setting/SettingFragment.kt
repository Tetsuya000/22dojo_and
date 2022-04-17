package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.SettingFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.constants.CharacterConstants.CHANGE_IMAGE_NUMBER
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.collectWhenStarted

@AndroidEntryPoint
class SettingFragment : Fragment(R.layout.setting_fragment) {

    private var _binding: SettingFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by activityViewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SettingFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        binding.cardEditCharacter.setOnClickListener {
            val action = SettingFragmentDirections.navigateSettingToEditCharacterDialog()
            findNavController().navigate(action)
        }

        observeCharacterLevel()
    }

    private fun observeCharacterLevel() =
        viewModel.characterLevel.collectWhenStarted(viewLifecycleOwner) { level ->
            changeCharacter(level.toInt())
        }

    private fun changeCharacter(level: Int) = when (level / CHANGE_IMAGE_NUMBER) {
        0    -> binding.imageCharacter.load(R.drawable.character_01)
        1    -> binding.imageCharacter.load(R.drawable.character_02)
        2    -> binding.imageCharacter.load(R.drawable.character_03)
        3    -> binding.imageCharacter.load(R.drawable.character_04)
        else -> binding.imageCharacter.load(R.drawable.character_05)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
