package com.example.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.BaseApplication
import com.example.notesapp.LoginApplication
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentLoginBinding
import com.example.notesapp.databinding.FragmentPasscodeBinding
import com.example.notesapp.model.Login
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.LoginViewModel
import com.example.notesapp.viewmodel.LoginViewModelFactory
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelFactory
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment() {

    // Variable containing an argument from the note detail fragment
    private val navigationArgs: NoteDetailFragmentArgs by navArgs()

    // Sets up view model factory
    private val viewModel: LoginViewModel by activityViewModels {
        LoginViewModelFactory(
            (activity?.application as LoginApplication).database.loginDao()
        )
    }

    // Variable to be used to contain the note that will be used in the future
    private lateinit var login: Login

    // Fragment binding
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        // Inflates layout
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var logins = viewModel.allLogins
        binding.apply {
            loginButton.setOnClickListener {
                logins.value!!.forEach {
                    if (usernameInput.text.toString() == it.username) {
                        val action = R.id.action_loginFragment_to_homeFragment
                        findNavController().navigate(action)
                    }
                    else {

                    }
                }

            }
        }
    }


}