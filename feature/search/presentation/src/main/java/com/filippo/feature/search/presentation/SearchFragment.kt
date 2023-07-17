package com.filippo.feature.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.filippo.core.ui.BaseFragment
import com.filippo.feature.search.presentation.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val viewModel by viewModels<SearchViewModel>()
    private val searchAdapter by lazy { SearchAdapter(::onMovieClick) }

    override fun bindingFactory(inflater: LayoutInflater) =
        FragmentSearchBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        collectData()
    }

    private fun setupViews() {
        binding.searchResults.adapter = searchAdapter
            .withLoadStateFooter(SearchLoadStateAdapter(searchAdapter::retry))

        binding.query.doAfterTextChanged { viewModel.search(it?.toString()) }
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.searchItems.collectLatest(searchAdapter::submitData)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchAdapter.loadStateFlow.collect {
                val isInitialLoading = it.refresh is LoadState.Loading
                val isInitialError = it.refresh is LoadState.Error
                binding.errorLayout.isVisible = isInitialError
                binding.progress.isVisible = isInitialLoading
                binding.errorMessage.text = (it.refresh as? LoadState.Error)?.error?.message
            }
        }
    }

    private fun onMovieClick(id: Int) {
        findNavController().navigate(
            NavDeepLinkRequest.Builder
                .fromUri("multi-module://movie-details/$id".toUri())
                .build()
        )
    }
}
