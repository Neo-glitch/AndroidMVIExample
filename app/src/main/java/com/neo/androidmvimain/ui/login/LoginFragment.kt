package com.neo.androidmvimain.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.neo.androidmvimain.MainActivity
import com.neo.androidmvimain.R
import com.neo.androidmvimain.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        // when view is resumed run this code in the block
        lifecycleScope.launchWhenResumed {
            viewModel.viewState.collect{viewState ->
                processViewState(viewState)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signInButton.setOnClickListener {
//            navigateToProfile()
            viewModel.signInButtonClicked()
        }

        binding.emailInput.doOnTextChanged { text, _, _, _ ->
            viewModel.emailChanged(
                text?.toString().orEmpty()
            )  // returns string if null or not null here
        }

        binding.passwordInput.doOnTextChanged { text, _, _, _ ->
            viewModel.passwordChanged(text?.toString().orEmpty())
        }
    }


    private fun processViewState(viewState: LoginViewState) {
        // reacting to the viewState

        binding.progressBar.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE

    }

    private fun navigateToProfile() {
        (activity as MainActivity).navigateToProfile()
    }


}