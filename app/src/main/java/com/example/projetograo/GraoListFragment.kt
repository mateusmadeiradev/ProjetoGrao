package com.example.projetograo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetograo.adapter.GraoAdapter
import com.example.projetograo.databinding.FragmentGraoListBinding
import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher

class GraoListFragment : Fragment() {
    private lateinit var viewModel: GraoViewModel
    private lateinit var adapter: GraoAdapter
    private var _binding: FragmentGraoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentGraoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(GraoViewModel::class.java)

        // Configura o layout do RecyclerView
        binding.grainList.layoutManager = LinearLayoutManager(context)

        // Observa a lista de grãos no ViewModel e atualiza o adapter
        viewModel.allGraos.observe(viewLifecycleOwner, Observer { graos ->
            adapter = GraoAdapter(graos, { grao ->
                // Navegar para a tela de edição com o ID do Grão usando um Bundle
                val bundle = Bundle().apply {
                    putInt("graoId", grao.id) // Supondo que `id` é do tipo Int
                }
                findNavController().navigate(R.id.action_graoListFragment_to_addGraoFragment, bundle)
            }, { grao ->
                // Excluir o Grão selecionado
                viewModel.deleteGrao(grao)
            })
            binding.grainList.adapter = adapter
        })

        // Configura o botão para adicionar um novo grão
        binding.addGrainButton.setOnClickListener {
            findNavController().navigate(R.id.action_graoListFragment_to_addGraoFragment)
        }

        // Configura o botão de teste para exibir dados
//        binding.testBot.setOnClickListener {
//            viewModel.allGraos.observe(viewLifecycleOwner, Observer { graos ->
//                val graoInfo = graos.joinToString("\n") { grao ->
//                    "Nome: ${grao.nome}, Quantidade: ${grao.quantidade}, Peso: ${grao.pesoTotal}"
//                }
//                AlertDialog.Builder(requireContext())
//                    .setTitle("Dados dos Grãos")
//                    .setMessage(graoInfo.ifEmpty { "Nenhum dado encontrado" })
//                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
//                    .show()
//            })
//        }

        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                viewModel.searchGraos(query).observe(viewLifecycleOwner, Observer { graos ->
                    adapter = GraoAdapter(graos, { grao ->
                        val bundle = Bundle().apply {
                            putInt("graoId", grao.id)
                        }
                        findNavController().navigate(R.id.action_graoListFragment_to_addGraoFragment, bundle)
                    }, { grao ->
                        viewModel.deleteGrao(grao)
                    })
                    binding.grainList.adapter = adapter
                })
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Evita vazamentos de memória
    }
}
