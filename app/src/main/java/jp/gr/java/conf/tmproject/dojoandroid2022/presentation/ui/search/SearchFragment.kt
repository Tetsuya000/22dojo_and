package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.SearchFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.collectWhenStarted
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extension.showKeyboard

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.search_fragment) {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SearchFragmentBinding.bind(view)

        initUi()
        setUpAction()
        setUpToolbar()
        observeSearchResponse()
    }

    private fun initUi() {
        showKeyboard(binding.searchText)
        binding.searchText.setText("Android/Kotlin")
    }

    private fun setUpAction() {
        binding.searchText.setOnEditorActionListener { editText, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = editText.text.toString()
                viewModel.searchRepository(searchText)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setUpToolbar() {
        val toolbar = binding.searchToolbar
        toolbar.setupWithNavController(findNavController())
    }

    private fun observeSearchResponse() =
        viewModel.searchResult.collectWhenStarted(viewLifecycleOwner) { searchResponse ->
            searchResponse?.let { response ->
                val sortedRepository = response.items.sortedByDescending { it.starCount }
                sortedRepository.forEach {
                    println(it.fullName)
                    println(it.starCount)
                }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
