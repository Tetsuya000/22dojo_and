package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.memo.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.MemoEditFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbarError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemoEditFragment : Fragment(R.layout.memo_edit_fragment) {

    private var _binding: MemoEditFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MemoEditViewModel by viewModels()
    private val navArgs by navArgs<MemoEditFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = MemoEditFragmentBinding.bind(view)

        binding.editMemo.setText(navArgs.node.memo)

        binding.buttonSave.setOnClickListener {
            val node = navArgs.node
            val memo = binding.editMemo.text.toString()
            viewModel.saveNode(node, memo)
        }

        setUpToolbar()
        observe()
    }

    private fun setUpToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun observe() {
        observeSaveState()
        observeErrorState()
    }

    private fun observeSaveState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isSaveSuccess.collect {
                findNavController().popBackStack()
            }
        }
    }

    private fun observeErrorState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isError.collect {
                makeSnackbarError(
                    requireContext(), binding.buttonSave, getString(R.string.error_failed_to_save)
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
