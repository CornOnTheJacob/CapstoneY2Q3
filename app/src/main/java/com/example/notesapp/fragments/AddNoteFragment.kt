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

    private lateinit var notes: Notes

    // Sets up fragment binding
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    //private val navigationArgs: AddNoteFragmentArgs by navArgs()


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

        // Adds a new note when the create button is clicked
        binding.createButton.setOnClickListener {
            addNote()
        }
        /*
        val data = ArrayList<ItemsViewModel>()
        for (i in 1..20) {
            data.add(ItemsViewModel(i.toLong(), "Note " + i, "11/11/2011", false, "ffdgfdgfd"))
        }

        // This will pass the ArrayList to our Adapter

        val adapter = CustomAdapter(data)



        binding.apply {
            recyclerview.adapter = adapter
            floatingActionButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_homeFragment_to_addNoteFragment
                )
            }
        }

         */
    }

    // Function adds note to view model
    private fun addNote() {
        if (isValidEntry()) {

            // Gets current date
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())

            // Checks which radio button is chosen (yes or no)
            var securityStatus: Boolean = false
            if (binding.yesSecurity.isChecked) {
                securityStatus = true
            }

            // Adds a new note to the view model
            viewModel.addNote(
                binding.nameInput.text.toString(),
                currentDate,
                securityStatus,
                binding.passcodeInput.text.toString(),
                "grgbtht"
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
                securityStatus = true,
                notes = "grgbtht",
                password = binding.passcodeInput.text.toString()
            )
            findNavController().navigate(
                R.id.action_addNoteFragment_to_homeFragment
            )
        }
    }

    /*
    // Binds note to view model
    private fun bindNote(note: Notes) {
        binding.apply{
            nameInput.setText(note.title, TextView.BufferType.SPANNABLE)
            sta.isChecked = forageable.inSeason
            notesInput.setText(forageable.notes, TextView.BufferType.SPANNABLE)
            saveBtn.setOnClickListener {
                updateForageable()
            }
        }

    }

     */

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString()
    )
}