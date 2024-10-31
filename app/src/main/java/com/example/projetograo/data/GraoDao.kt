package com.example.projetograo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface GraoDao {
    @Insert
    suspend fun insert(grao: Grao)

    @Update
    suspend fun update(grao: Grao)

    @Delete
    suspend fun delete(grao: Grao)

    @Query("SELECT * FROM graos")
    fun getAllGraos(): Flow<List<Grao>>

    @Query("SELECT * FROM graos WHERE id = :id LIMIT 1")
    fun getGraoById(id: Int): LiveData<Grao?>

    @Query("SELECT * FROM graos WHERE nome LIKE :searchQuery")
    fun searchGraos(searchQuery: String): Flow<List<Grao>>
}
