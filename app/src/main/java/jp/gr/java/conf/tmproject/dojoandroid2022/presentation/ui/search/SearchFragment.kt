package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.SearchFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.gone
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.hideKeyboard
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.showKeyboard
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.visible

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchResponseController: SearchResponseController

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchFragmentBinding.bind(view)

        initUi()
        setupToolbar()
        setupSearchAction()
        setupRecyclerView()
        observe()
    }

    private fun initUi() {
        showKeyboard(binding.searchText)
        binding.searchText.setText("Android/Kotlin")
    }

    private fun setupToolbar() {
        val toolbar = binding.searchToolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun setupSearchAction() {
        binding.searchText.setOnEditorActionListener { editText, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = editText.text.toString()
                viewModel.searchRepository(searchText)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setupRecyclerView() {
        searchResponseController = SearchResponseController(object : SearchResponseController.SelectListener {
            override fun onSelected(url: String) {
                val action = SearchFragmentDirections.navigateSearchToWebView(url)
                findNavController().navigate(action)
                hideKeyboard()
            }
        })

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.apply {
            adapter = searchResponseController.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(decoration)
        }
    }

    private fun observe() {
        observeLoadState()
        observeSearchResponse()
    }

    private fun observeSearchResponse() =
        viewModel.searchResult.collectWhenStarted(viewLifecycleOwner) { searchResponse ->
            searchResponse?.let { response ->
                val sortedRepository = response.items.sortedByDescending { it.starCount }
                searchResponseController.setData(sortedRepository)
            }
        }

    private fun observeLoadState() = viewModel.loadState.collectWhenStarted(viewLifecycleOwner) { state ->
        when (state) {
            is LoadState.Nothing -> Unit
            is LoadState.Loading -> binding.progressBar.visible()
            is LoadState.Done -> binding.progressBar.gone()
            is LoadState.Error -> binding.progressBar.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
