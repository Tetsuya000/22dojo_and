package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.memo.detail

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
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.MemoDetailFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.makeSnackbarError
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MemoDetailFragment : Fragment(R.layout.memo_detail_fragment) {

    private var _binding: MemoDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MemoDetailViewModel by viewModels()
    private val navArgs by navArgs<MemoDetailFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MemoDetailFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        viewModel.setSelectedNodeMemo(navArgs.node.id)

        binding.fabEdit.setOnClickListener {
            val action = MemoDetailFragmentDirections.navigateDetailToEdit(navArgs.node)
            findNavController().navigate(action)
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.deleteNode(navArgs.node)
        }

        setUpToolbar()
        observe()
    }

    private fun setUpToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun observe() {
        observeDeleteState()
        observeErrorState()
    }

    private fun observeDeleteState() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.isDeleteSuccess.collect {
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
