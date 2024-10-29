package com.example.projetograo

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.example.projetograo.data.Grao
import com.example.projetograo.databinding.FragmentAddGraoBinding
import androidx.lifecycle.ViewModelProvider


class AddGraoFragment : Fragment() {
    private lateinit var viewModel: GraoViewModel
    private lateinit var binding: FragmentAddGraoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGraoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraoViewModel::class.java)

        binding.saveGraoButton.setOnClickListener {
            val nome = binding.nome.text.toString()
            val quantidade = binding.quantidade.text.toString().toDoubleOrNull() ?: 0.0
            val pesoTotal = binding.pesoTotal.text.toString().toDoubleOrNull() ?: 0.0
            val nomeProdutor = binding.nomeProdutor.text.toString()
            val telefone = binding.telefone.text.toString()
            val endereco = binding.endereco.text.toString()

            val grao = Grao(
                nome = nome,
                quantidade = quantidade,
                pesoTotal = pesoTotal,
                nomeProdutor = nomeProdutor,
                telefone = telefone,
                endereco = endereco
            )

            viewModel.insert(grao)
            findNavController(view).navigateUp()

        }
    }
}
