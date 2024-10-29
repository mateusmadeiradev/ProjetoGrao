package com.example.projetograo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "graos")
data class Grao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val quantidade: Double,
    val pesoTotal: Double,
    val nomeProdutor: String,
    val telefone: String,
    val endereco: String
)
