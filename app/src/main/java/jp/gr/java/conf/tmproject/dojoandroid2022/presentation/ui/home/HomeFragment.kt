package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.HomeFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.constants.CharacterConstants.CHANGE_IMAGE_NUMBER
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.collectWhenStarted

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = HomeFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        observeCharacterLevel()
    }

    private fun observeCharacterLevel() =
        viewModel.characterLevel.collectWhenStarted(viewLifecycleOwner) { level ->
            changeBackgroundAndCharacter(level.toInt())
        }

    private fun changeBackgroundAndCharacter(level: Int) = when (level / CHANGE_IMAGE_NUMBER) {
        0    -> {
            binding.background.setBackgroundResource(R.drawable.bg_01)
            binding.imageCharacter.load(R.drawable.character_01)
        }
        1    -> {
            binding.background.setBackgroundResource(R.drawable.bg_02)
            binding.imageCharacter.load(R.drawable.character_02)
        }
        2    -> {
            binding.background.setBackgroundResource(R.drawable.bg_03)
            binding.imageCharacter.load(R.drawable.character_03)
        }
        3    -> {
            binding.background.setBackgroundResource(R.drawable.bg_04)
            binding.imageCharacter.load(R.drawable.character_04)
        }
        else -> {
            binding.background.setBackgroundResource(R.drawable.bg_05)
            binding.imageCharacter.load(R.drawable.character_05)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
