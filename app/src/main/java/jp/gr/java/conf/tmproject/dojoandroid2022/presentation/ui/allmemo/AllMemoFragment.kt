package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.allmemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.AllMemoFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted

@AndroidEntryPoint
class AllMemoFragment : Fragment(R.layout.all_memo_fragment) {
    private var _binding: AllMemoFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AllMemoViewModel by viewModels()
    private lateinit var allMemoController: AllMemoController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = AllMemoFragmentBinding.bind(view)
        setUpToolbar()
        setUpRecyclerView()
        loadAllMemo()
    }

    private fun setUpToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.title = "Memo"
    }

    private fun setUpRecyclerView() {
        allMemoController = AllMemoController(object : AllMemoController.SelectListener {
            override fun onSelected(memo: Memo) {
                val action = AllMemoFragmentDirections.navigateAllMemoToDetailMemo(memo.nodeId, memo.title, memo.memo)
                findNavController().navigate(action)
            }
        })

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.apply {
            adapter = allMemoController.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(decoration)
        }
    }

    private fun loadAllMemo() = viewModel.memoList.collectWhenStarted(viewLifecycleOwner) { memoList ->
        allMemoController.setData(memoList)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}
