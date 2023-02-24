package com.example.notesapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.notesapp.BaseApplication
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelFactory
import com.example.notesapp.databinding.FragmentNoteDetailBinding
import com.example.notesapp.model.Notes

/**
 * This fragment displays the information in of the notes and allow the user to enter their notes
 */
class NoteDetailFragment : Fragment() {

    // Variable containing an argument from the note detail fragment
    private val navigationArgs: NoteDetailFragmentArgs by navArgs()

    // Sets up view model factory
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    private lateinit var notes: Notes

    // Fragment binding
    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.id
        Log.e("This", id.toString())
        viewModel.getById(id).observe(viewLifecycleOwner) {
            if (it == null) {
                return@observe
            }
            notes = it
            bindNote()
        }
    }

    // Custom function for binding the data from the database to the fragment
    private fun bindNote() {
        binding.apply {
            if (notes.notes.isBlank()) {
                text.setText("x")
            } else {
                text.setText(notes.notes)
            }
            text.visibility = View.INVISIBLE
        }
    }
}