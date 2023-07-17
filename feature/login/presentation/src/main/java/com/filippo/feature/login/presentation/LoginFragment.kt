package com.filippo.feature.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.filippo.core.navigation.CrossModuleNavigator
import com.filippo.core.navigation.NavDestination
import com.filippo.core.ui.BaseFragment
import com.filippo.feature.login.domain.LoginState
import com.filippo.feature.login.domain.ValidationError
import com.filippo.feature.login.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun bindingFactory(inflater: LayoutInflater): FragmentLoginBinding =
        FragmentLoginBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectEvents()
        setupListeners()
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loginState.collect { state ->
                with(binding) {
                    progress.isVisible = state == LoginState.Loading
                    requestError.isVisible =
                        state is LoginState.Error && state.errors.any { it is ValidationError.Request }
                    handleErrors((state as? LoginState.Error)?.errors.orEmpty())
                }
                if (state is LoginState.Success) {
                    Toast.makeText(context, R.string.log_in_successful, Toast.LENGTH_LONG).show()
                    CrossModuleNavigator.navigateTo(NavDestination.Account, findNavController())
                }
            }
        }
    }

    private fun handleErrors(credentialErrors: List<ValidationError>) {
        with(binding) {
            usernameInputLayout.error = credentialErrors
                .find { it is ValidationError.Username }
                ?.message

            passwordInputLayout.error = credentialErrors
                .find { it is ValidationError.Password }
                ?.message

            requestError.text = credentialErrors
                .find { it is ValidationError.Request }
                ?.message
        }
    }

    private fun setupListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                val username = username.text?.toString().orEmpty()
                val password = password.text?.toString().orEmpty()

                viewModel.login(username, password)
            }
        }
    }
}
