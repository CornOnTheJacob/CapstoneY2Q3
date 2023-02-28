package com.example.notesapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.BaseApplication
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteDetailBinding
import com.example.notesapp.databinding.FragmentPasscodeBinding
import com.example.notesapp.model.Notes
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class PasscodeFragment : Fragment() {

    // Variable containing an argument from the note detail fragment
    private val navigationArgs: NoteDetailFragmentArgs by navArgs()

    // Sets up view model factory
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    // Variable to be used to contain the note that will be used in the future
    private lateinit var notes: Notes

    // Fragment binding
    private var _binding: FragmentPasscodeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPasscodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binds data from database to corresponding values by id
        val id = navigationArgs.id
        viewModel.getById(id).observe(viewLifecycleOwner) {
            if (it == null) {
                return@observe
            }
            notes = it
            bindNote()
        }
    }

    private fun bindNote() {
        binding.apply {

            enterButton.setOnClickListener {
                // Correct passcode entry
                if (codeInput.text.toString() == notes.passcode) {
                    codeInput.text = null
                    var action = PasscodeFragmentDirections
                        .actionPasscodeFragmentToNoteDetailFragment(notes.id)
                    findNavController().navigate(action)
                }
                // Incorrect passcode entry
                else {
                    codeInput.text = null
                    codeInput.setBackgroundColor(Color.RED)
                }
            }
        }
    }
}