package org.d3if3034.miniproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3034.miniproject.model.Praktikum

@Dao
interface PraktikumDao {
    @Insert
    suspend fun insert(praktikum: Praktikum)

    @Update
    suspend fun update(praktikum: Praktikum)

    @Query("SELECT * FROM praktikum ORDER BY tanggal DESC")
    fun getPraktikum(): Flow<List<Praktikum>>

    @Query("SELECT * FROM praktikum WHERE id = :id")
    suspend fun getPraktikumById(id: Long): Praktikum?

    @Query("DELETE FROM praktikum WHERE id = :id")
    suspend fun deleteById(id: Long)
}