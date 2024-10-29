package com.example.projetograo.adapter

import com.example.projetograo.R
import com.example.projetograo.data.Grao
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GraoAdapter(private val graos: List<Grao>) : RecyclerView.Adapter<GraoAdapter.GraoViewHolder>() {

    class GraoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.nome)
        val quantidade: TextView = itemView.findViewById(R.id.quantidade)
        val pesoTotal: TextView = itemView.findViewById(R.id.pesoTotal)
        val nomeProdutor: TextView = itemView.findViewById(R.id.nomeProdutor)
        val telefone: TextView = itemView.findViewById(R.id.telefone)
        val endereco: TextView = itemView.findViewById(R.id.endereco)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_grao, parent, false)
        return GraoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GraoViewHolder, position: Int) {
        val currentGrain = graos[position]
        holder.nome.text = currentGrain.nome
        holder.quantidade.text = "Quantidade de Sacas: ${currentGrain.quantidade}"
        holder.pesoTotal.text = "Peso Total: ${currentGrain.pesoTotal}"
        holder.nomeProdutor.text = "Produtor: ${currentGrain.nomeProdutor}"
        holder.telefone.text = "Telefone: ${currentGrain.telefone}"
        holder.endereco.text = "Endereço: ${currentGrain.endereco}"
    }

    override fun getItemCount() = graos.size
}