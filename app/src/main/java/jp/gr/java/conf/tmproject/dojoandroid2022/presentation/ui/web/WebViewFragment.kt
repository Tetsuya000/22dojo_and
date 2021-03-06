package jp.gr.java.conf.tmproject.dojoandroid2022.presentation.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.gr.java.conf.tmproject.dojoandroid2022.R
import jp.gr.java.conf.tmproject.dojoandroid2022.databinding.WebViewFragmentBinding
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.LoadState
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.collectWhenStarted
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.gone
import jp.gr.java.conf.tmproject.dojoandroid2022.presentation.util.extensions.visible

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.web_view_fragment) {

    private var _binding: WebViewFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WebViewViewModel by viewModels()
    private val navArgs by navArgs<WebViewFragmentArgs>()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = WebViewFragmentBinding.bind(view)
        setupToolbar()
        setupWebView()
        setupSwipeRefresh()
        setupBackPressedDispatcher()
        observeLoadState()
    }

    private fun setupToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        toolbar.setupWithNavController(findNavController())
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(
                view: WebView?,
                url: String?
            ) {

                binding.swipeRefresh.isRefreshing = false
                viewModel.changeLoadState(LoadState.Done)
            }
        }

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(navArgs.url)
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.isRefreshing = false

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            binding.webView.reload()
        }
    }

    private fun setupBackPressedDispatcher() =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                findNavController().popBackStack()
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
