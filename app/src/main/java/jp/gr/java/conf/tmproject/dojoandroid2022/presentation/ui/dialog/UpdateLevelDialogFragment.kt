package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.UpdateLevelDialogFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.visible

@AndroidEntryPoint
class UpdateLevelDialogFragment : DialogFragment() {

    private var _binding: UpdateLevelDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<UpdateLevelDialogFragmentArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = UpdateLevelDialogFragmentBinding.inflate(LayoutInflater.from(requireContext()))

        binding.buttonNegative.setOnClickListener {
            dismiss()
        }

        setTitleLevelUpOrDown()
        updateLevelText()
        return AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).setView(binding.root).create()
    }

    private fun setTitleLevelUpOrDown() {
        val oldLevel = navArgs.oldLevel
        val currentLevel = navArgs.currentLevel

        when (currentLevel - oldLevel) {
            1 -> binding.textLevelUpOrDown.text = getString(R.string.text_level_up)
            -1 -> binding.textLevelUpOrDown.text = getString(R.string.text_level_down)
        }
        changeCharacterImage(oldLevel, binding.imageBeforeCharacter)
        changeCharacterImage(currentLevel, binding.imageAfterCharacter)
    }

    private fun updateLevelText() {
        binding.textBeforeLevel.text = navArgs.oldLevel.toString()
        binding.textAfterLevel.text = navArgs.currentLevel.toString()
    }

    private fun changeCharacterImage(
        level: Int,
        image: ImageView
    ) {

//        image.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.character_01))

        image.visible()
        when (level / 5) {
            0 -> {
                image.setImageResource(R.drawable.character_01)
            }
            1 -> {
                image.setImageResource(R.drawable.character_02)
            }
            2 -> {
                image.setImageResource(R.drawable.character_03)
            }
            3 -> {
                image.setImageResource(R.drawable.character_04)
            }
            4 -> {
                image.setImageResource(R.drawable.character_05)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
