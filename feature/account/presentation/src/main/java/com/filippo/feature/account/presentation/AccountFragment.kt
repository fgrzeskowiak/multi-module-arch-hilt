package com.filippo.feature.account.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.filippo.core.navigation.CrossModuleNavigator
import com.filippo.core.navigation.NavDestination
import com.filippo.core.ui.BaseFragment
import com.filippo.feature.account.presentation.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AccountFragment : BaseFragment<FragmentAccountBinding>() {
    private val viewModel by viewModels<AccountViewModel>()

    override fun bindingFactory(inflater: LayoutInflater): FragmentAccountBinding =
        FragmentAccountBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        collectEvents()
    }

    private fun setupListeners() {
        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                with(binding) {
                    progress.isVisible = state == AccountScreenState.Loading
                    successGroup.isVisible = state is AccountScreenState.Success
                    errorLayout.isVisible = state is AccountScreenState.Failure
                    when (state) {
                        is AccountScreenState.Failure -> errorMessage.text = state.error.message
                        AccountScreenState.NeedsLogin -> openLoginScreen()
                        is AccountScreenState.Success -> {
                            name.text = state.accountDetails.name
                            username.text = state.accountDetails.username
                        }
                        AccountScreenState.Loading -> {
                        }
                    }
                }
            }
        }
    }

    private fun openLoginScreen() {
        CrossModuleNavigator.navigateTo(
            NavDestination.Login(origin = NavDestination.Account),
            findNavController()
        )
    }
}
