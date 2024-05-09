package org.d3if3034.miniproject.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3034.miniproject.database.PraktikumDao
import org.d3if3034.miniproject.model.Praktikum
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DetailViewModel(private val dao: PraktikumDao) : ViewModel() {

    private  val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    fun insert(judul: String, lab: String, rangkuman: String) {
        val praktikum = Praktikum (
            judul = judul,
            lab = lab,
            rangkuman = rangkuman,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(praktikum)
        }
    }
    suspend fun getPraktikum(id: Long): Praktikum? {
        return dao.getPraktikumById(id)
    }

    fun update(id: Long, judul: String, lab: String, rangkuman: String) {
        val praktikum = Praktikum (
            id = id,
            judul = judul,
            lab = lab,
            rangkuman = rangkuman,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(praktikum)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}
