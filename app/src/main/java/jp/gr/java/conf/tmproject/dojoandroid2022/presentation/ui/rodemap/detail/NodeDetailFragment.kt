package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.detail

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
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.NodeDetailFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbarError
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NodeDetailFragment : Fragment(R.layout.node_detail_fragment) {

    private var _binding: NodeDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NodeDetailViewModel by viewModels()
    private val navArgs by navArgs<NodeDetailFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NodeDetailFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        viewModel.setSelectedNodeMemo(navArgs.node.id)

        binding.fabEdit.setOnClickListener {
            val action = NodeDetailFragmentDirections.navigateDetailToEdit(navArgs.node)
            findNavController().navigate(action)
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteNode(navArgs.node)
        }

        observe()
    }

    private fun observe() {
        observeSaveState()
        observeErrorState()
    }

    private fun observeSaveState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isDeleteSuccess.collect {
                showToast(requireContext(), getString(R.string.success_delete_node))
                findNavController().popBackStack()
            }
        }
    }

    private fun observeErrorState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isError.collect {
                makeSnackbarError(
                    requireContext(), binding.buttonDelete, getString(R.string.error_failed_to_delete)
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
