package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapter.CustomAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.*
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.viewmodel.NotesViewModel
import com.example.notesapp.viewmodel.NotesViewModelFactory

class HomeFragment : Fragment() {
    private val viewModel: NotesViewModel by activityViewModels {
        NotesViewModelFactory(
            (activity?.application as BaseApplication).database.notesDao()
        )
    }

    // Binding setup
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CustomAdapter {
            val action = R.id.action_homeFragment_to_noteDetailFragment
            this.findNavController().navigate(action)
        }
        binding.recyclerview.adapter = adapter

        //
        viewModel.allNotes.observe(this.viewLifecycleOwner) {items ->
            items.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.floatingActionButton.setOnClickListener {
            val action = R.id.action_homeFragment_to_addNoteFragment
            this.findNavController().navigate(action)
        }
    }
}