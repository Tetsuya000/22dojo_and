package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.EditCharacterDialogFragmentBinding

@AndroidEntryPoint
class EditCharacterDialogFragment : DialogFragment() {

    private var _binding: EditCharacterDialogFragmentBinding? = null
    private val binding get() = _binding!!
    val viewModel: EditCharacterDialogViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = EditCharacterDialogFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        binding.buttonPositive.setOnClickListener {
            val newCharacterName = binding.editCharacterName.text.toString()
            val isBlank = checkBlankCharacterName(newCharacterName)
            val isOver = checkOverCharacterName(newCharacterName)
            if (isBlank || isOver) return@setOnClickListener

            viewModel.saveCharacterName(newCharacterName)
            dismiss()
        }

        binding.buttonNegative.setOnClickListener {
            dismiss()
        }

        return AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog).setView(binding.root).create()
    }

    private fun checkBlankCharacterName(newCharacterName: String): Boolean {
        val isBlank = newCharacterName == ""
        if (isBlank) binding.textInputLayout.error = getString(R.string.error_empty)
        return isBlank
    }

    private fun checkOverCharacterName(newCharacterName: String): Boolean {
        val isOver = newCharacterName.length > 10
        if (isOver) binding.textInputLayout.error = getString(R.string.error_over, 10)
        return isOver
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
