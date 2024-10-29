package com.example.projetograo.data

import kotlinx.coroutines.flow.Flow

class GraoRepository(private val graoDao: GraoDao) {
    val allGraos: Flow<List<Grao>> = graoDao.getAllGraos()

    suspend fun insert(grao: Grao) {
        graoDao.insert(grao)
    }

    suspend fun update(grain: Grao) {
        graoDao.update(grain)
    }

    suspend fun delete(grain: Grao) {
        graoDao.delete(grain)
    }
}
