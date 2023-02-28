package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.CustomAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.notesapp.*
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelFactory

class HomeFragment : Fragment() {

    // View model factory setup
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    // Binding setup
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        // Inflates layout
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var action: NavDirections
        // Uses custom adapter
        val adapter = CustomAdapter { notes ->

            // If there is no passcode goes to details
            if (notes.passcode == "") {
                action = HomeFragmentDirections
                    .actionHomeFragmentToNoteDetailFragment(notes.id)
            }
            // If there is a passcode goes to passcode checker
            else {
                action = HomeFragmentDirections
                    .actionHomeFragmentToPasscodeFragment(notes.id)
            }

            this.findNavController().navigate(action)
        }
        binding.recyclerview.adapter = adapter

        // Passes adapter on all notes and enters notes values in recycler view
        viewModel.allNotes.observe(this.viewLifecycleOwner) {items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)

        // goes to add note fragment when clicked
        binding.floatingActionButton.setOnClickListener {
            val action = R.id.action_homeFragment_to_addNoteFragment
            this.findNavController().navigate(action)
        }
    }
}