package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.rodemap.memo.detail

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
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.dialog.edit.EditMemoDialogFragment

@AndroidEntryPoint
class DetailMemoFragment : Fragment(R.layout.detail_memo_fragment) {

    private var _binding: DetailMemoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailMemoViewModel by viewModels()
    private val navArgs by navArgs<DetailMemoFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DetailMemoFragmentBinding.bind(view)
        binding.also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        setUpToolbar()
        viewModel.loadMemoById(navArgs.nodeId)

        binding.textMemo.setOnClickListener {
            showEditMemoDialog()
        }
    }

    private fun setUpToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.inflateMenu(R.menu.edit_menu)
        toolbar.setupWithNavController(findNavController())
        setUpMenuItemClickListener(toolbar)
    }

    private fun setUpMenuItemClickListener(toolbar: MaterialToolbar) = toolbar.setOnMenuItemClickListener {
        when (it.itemId) {
            R.id.item_edit_memo -> {
                showEditMemoDialog()
                true
            }
            else                -> false
        }
    }

    private fun showEditMemoDialog() {
        val dialogFragment = EditMemoDialogFragment()
        val args = Bundle()
        args.putInt("nodeId", navArgs.nodeId)
        args.putString("nodeTitle", navArgs.nodeTitle)
        args.putString("memo", viewModel.memo.value)
        dialogFragment.arguments = args
        dialogFragment.show(parentFragmentManager, "EditMemoDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
