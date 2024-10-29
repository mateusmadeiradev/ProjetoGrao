package com.example.projetograo.data

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
}
