package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.memo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.MemoListFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.domain.model.Memo
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted

@AndroidEntryPoint
class MemoListFragment : Fragment(R.layout.memo_list_fragment) {

    private var _binding: MemoListFragmentBinding? = null
    private val binding get() = _binding!!
    private val listViewModel: MemoListViewModel by viewModels()
    private lateinit var memoListController: MemoListController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = MemoListFragmentBinding.bind(view)
        setupToolbar()
        setupRecyclerView()
        loadAllMemo()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.title = "Memo"
    }

    private fun setupRecyclerView() {
        memoListController = MemoListController(object : MemoListController.SelectListener {
            override fun onSelected(memo: Memo) {
                val action = MemoListFragmentDirections.navigateMemoListToDetailMemo(memo.nodeId, memo.title, memo.memo)
                findNavController().navigate(action)
            }
        })

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.apply {
            adapter = memoListController.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(decoration)
        }
    }

    private fun loadAllMemo() = listViewModel.memoList.collectWhenStarted(viewLifecycleOwner) { memoList ->
        memoListController.setData(memoList)
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}
