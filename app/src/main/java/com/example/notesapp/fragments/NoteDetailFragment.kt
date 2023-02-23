package com.example.notesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.notesapp.BaseApplication
import com.example.notesapp.NotesViewModel
import com.example.notesapp.NotesViewModelFactory
import com.example.notesapp.R
import com.example.notesapp.model.Notes

/**
 * This fragment displays the information in of the notes and allow the user to enter their notes
 */
class NoteDetailFragment : Fragment() {

    private val navigationArgs: NoteDetailFragmentArgs by navArgs()

    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    private lateinit var notes: Notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }
}