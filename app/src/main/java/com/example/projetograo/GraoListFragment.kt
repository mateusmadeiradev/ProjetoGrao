package com.example.projetograo

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetograo.adapter.GraoAdapter
import com.example.projetograo.databinding.FragmentGraoListBinding
import androidx.navigation.fragment.findNavController

class GraoListFragment : Fragment() {
    private lateinit var viewModel: GraoViewModel
    private lateinit var adapter: GraoAdapter
    private var _binding: FragmentGraoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraoViewModel::class.java)

        adapter = GraoAdapter(emptyList())
        binding.grainList.adapter = adapter
        binding.grainList.layoutManager = LinearLayoutManager(context)

        viewModel.allGraos.observe(viewLifecycleOwner, Observer { graos ->
            adapter = GraoAdapter(graos)
            binding.grainList.adapter = adapter
        })

        binding.addGrainButton.setOnClickListener {
            findNavController().navigate(R.id.action_graoListFragment_to_addGraoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evitar vazamentos de memória
    }
}
