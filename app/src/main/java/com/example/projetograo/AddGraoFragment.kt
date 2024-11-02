package com.example.projetograo

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import com.example.projetograo.data.Grao
import com.example.projetograo.databinding.FragmentAddGraoBinding
import androidx.lifecycle.ViewModelProvider

class AddGraoFragment : Fragment() {
    private lateinit var viewModel: GraoViewModel
    private var _binding: FragmentAddGraoBinding? = null
    private val binding get() = _binding!!

    // Variável para armazenar o ID do grão, se for uma edição
    private var graoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Recebe o ID do grão, se houver
        arguments?.let {
            graoId = it.getInt("graoId", -1) // -1 como valor padrão
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddGraoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraoViewModel::class.java)

        // Se um ID de grão foi recebido, carrega os dados desse grão
        graoId?.let { id ->
            viewModel.getGraoById(id).observe(viewLifecycleOwner) { grao ->
                grao?.let {
                    // Preenche os campos com os dados do grão
                    binding.nome.setText(it.nome)
                    binding.quantidade.setText(it.quantidade.toString())
                    binding.pesoTotal.setText(it.pesoTotal.toString())
                    binding.nomeProdutor.setText(it.nomeProdutor)
                    binding.telefone.setText(it.telefone)
                    binding.endereco.setText(it.endereco)
                }
            }
        }

        binding.saveGraoButton.setOnClickListener {
            val nome = binding.nome.text.toString().trim()
            val quantidade = binding.quantidade.text.toString().toDoubleOrNull() ?: 0.0
            val pesoTotal = binding.pesoTotal.text.toString().toDoubleOrNull() ?: 0.0
            val nomeProdutor = binding.nomeProdutor.text.toString().trim()
            val telefone = binding.telefone.text.toString().trim()
            val endereco = binding.endereco.text.toString().trim()

            // Verifica se algum campo obrigatório está vazio
            if (nome.isEmpty() || quantidade == null || pesoTotal == null || nomeProdutor.isEmpty() || telefone.isEmpty() || endereco.isEmpty()) {
                // Mostra uma mensagem de erro ao usuário
                Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Sai do listener sem prosseguir
            }

            val grao = Grao(
                id = graoId ?: 0,
                nome = nome,
                quantidade = quantidade,
                pesoTotal = pesoTotal,
                nomeProdutor = nomeProdutor,
                telefone = telefone,
                endereco = endereco
            )

            if (graoId == null) {
                viewModel.insert(grao) // Insere um novo grão
            } else {
                viewModel.update(grao) // Atualiza o grão existente
            }

            findNavController(view).navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Limpa a referência para evitar vazamentos de memória
        _binding = null
    }
}
