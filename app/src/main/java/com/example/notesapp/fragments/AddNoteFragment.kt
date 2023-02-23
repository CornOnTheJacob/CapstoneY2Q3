package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.adapter.CustomAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.*
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.model.Notes
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import androidx.navigation.fragment.navArgs
import kotlin.collections.ArrayList

/*
    Fragment that adds new notes to the view model
 */
class AddNoteFragment : Fragment() {

    private val navigationArgs: AddNoteFragmentArgs by navArgs()

    // Sets up fragment binding
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var notes: Notes

    // Refactor creation of view model to take an instance of NotesViewModelFactory
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        if (id > 0) {
            viewModel.getById(id).observe(viewLifecycleOwner) {
                notes = it
                bindNote(it)
            }

            binding.deleteButton.visibility = View.VISIBLE
            binding.deleteButton.setOnClickListener {
                deleteNote(notes)
            }
        } else {


            binding.yesSecurity.setOnClickListener {
                binding.apply {
                    passcodeTitle.visibility = View.VISIBLE
                    passcodeInput.visibility = View.VISIBLE
                }
            }

            binding.noSecurity.setOnClickListener {
                binding.apply {
                    passcodeTitle.visibility = View.INVISIBLE
                    passcodeInput.visibility = View.INVISIBLE
                }
            }

            // Adds a new note when the create button is clicked
            binding.createButton.setOnClickListener {
                addNote()
            }
        }
    }

    // Function adds note to view model
    private fun addNote() {
        if (isValidEntry()) {

            // Gets current date
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val currentDate = "Last Accessed: " + sdf.format(Date())

            // Adds a new note to the view model
            viewModel.addNote(
                binding.nameInput.text.toString(),
                currentDate,
                "grgbtht",
                binding.passcodeInput.text.toString()
            )

            findNavController().navigate(
                R.id.action_addNoteFragment_to_homeFragment
            )
        }
    }

    // Function deletes note from view model
    private fun deleteNote(note: Notes) {
        viewModel.deleteNote(note)
        findNavController().navigate(
            R.id.action_addNoteFragment_to_homeFragment
        )
    }

    // Updates note in view model
    private fun updateForageable() {
        if (isValidEntry()) {
            viewModel.updateNote(
                id = 0,
                title = binding.nameInput.text.toString(),
                lastAccessed = "11/11/2222",
                notes = "grgbtht",
                passcode = binding.passcodeInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addNoteFragment_to_homeFragment
            )
        }
    }


    // Binds note to view model
    private fun bindNote(note: Notes) {
        binding.apply{
            nameInput.setText(note.title, TextView.BufferType.SPANNABLE)
            passcodeInput.setText(note.passcode, TextView.BufferType.SPANNABLE)
            createButton.setOnClickListener {
                updateForageable()
            }
        }

    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString()
    )
}