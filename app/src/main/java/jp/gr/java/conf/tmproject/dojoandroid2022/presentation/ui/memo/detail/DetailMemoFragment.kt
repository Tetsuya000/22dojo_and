package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.memo.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.DetailMemoFragmentBinding

@AndroidEntryPoint
class DetailMemoFragment : Fragment(R.layout.detail_memo_fragment) {

    private var _binding: DetailMemoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMemoViewModel by viewModels()
    private val navArgs by navArgs<DetailMemoFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailMemoFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        setupToolbar()
        viewModel.loadMemoById(navArgs.nodeId)
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.inflateMenu(R.menu.edit_menu)
        toolbar.setupWithNavController(findNavController())
        setupMenuItemClickListener(toolbar)
    }

    private fun setupMenuItemClickListener(toolbar: MaterialToolbar) = toolbar.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.item_edit_memo -> {
                showEditMemoDialog()
                true
            }
            else -> false
        }
    }

    private fun showEditMemoDialog() {
        val action = DetailMemoFragmentDirections.navigateDetailMemoToEditMemoDialog(
            navArgs.nodeId, navArgs.nodeTitle, viewModel.memo.value
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
