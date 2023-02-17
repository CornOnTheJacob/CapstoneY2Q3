package com.example.notesapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlin.collections.ArrayList

class AddNoteFragment : Fragment() {

    //private val navigationArgs: AddNoteFragmentArgs by navArgs()

    private var _binding: FragmentAddNoteBinding? = null

    private lateinit var notes: Notes

    private val binding get() = _binding!!

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

    private fun addNote() {
        if (isValidEntry()) {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var securityStatus: Boolean = false
            if (binding.yesSecurity.isChecked) {
                securityStatus = true
            }
            viewModel.addNote(
                binding.nameInput.text.toString(),
                currentDate,
                securityStatus,
                binding.passcodeInput.text.toString(),
                ""
            )
            /*
            findNavController().navigate(
                R.id.action_addForageableFragment_to_forageableListFragment
            )

             */
        }
    }

    private fun isValidEntry() = viewModel.isValidEntry(
        binding.nameInput.text.toString(),
        binding.passcodeInput.text.toString()
    )
}