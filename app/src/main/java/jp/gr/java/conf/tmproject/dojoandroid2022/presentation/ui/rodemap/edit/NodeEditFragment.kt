package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.NodeEditFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbarError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NodeEditFragment : Fragment(R.layout.node_edit_fragment) {

    private var _binding: NodeEditFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NodeEditViewModel by viewModels()
    private val navArgs by navArgs<NodeEditFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = NodeEditFragmentBinding.bind(view)

        binding.editMemo.setText(navArgs.node.memo)

        binding.buttonSave.setOnClickListener {
            val node = navArgs.node
            val memo = binding.editMemo.text.toString()
            viewModel.saveNode(node, memo)
        }

        observe()
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
