package org.d3if3034.miniproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3034.miniproject.database.PraktikumDao
import org.d3if3034.miniproject.model.Praktikum

class MainViewModel(dao: PraktikumDao) : ViewModel() {
    val data: StateFlow<List<Praktikum>> = dao.getPraktikum().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}