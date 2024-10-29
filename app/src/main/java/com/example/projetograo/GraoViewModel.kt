package com.example.projetograo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.projetograo.data.Grao
import com.example.projetograo.data.GraoDatabase
import com.example.projetograo.data.GraoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: GraoRepository
    val allGraos: LiveData<List<Grao>>

    init {
        val graoDao = GraoDatabase.getDatabase(application).graoDao()
        repository = GraoRepository(graoDao)
        allGraos = repository.allGraos.asLiveData()
    }

    fun insert(grao: Grao) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(grao)
    }

    fun update(grao: Grao) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(grao)
    }

    fun delete(grao: Grao) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(grao)
    }
}
