package com.example.projetograo.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class GraoRepository(private val graoDao: GraoDao) {
    val allGraos: Flow<List<Grao>> = graoDao.getAllGraos()

    fun getGraoById(id: Int): LiveData<Grao?> {
        return graoDao.getGraoById(id)
    }

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
