package org.d3if3034.miniproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3034.miniproject.model.Praktikum

@Database(entities = [Praktikum::class], version = 1, exportSchema = false)
abstract class PraktikumDb : RoomDatabase() {

    abstract val dao: PraktikumDao

    companion object {
        @Volatile
        private var INSTANCE: PraktikumDb? = null

        fun getInstance(context: Context): PraktikumDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PraktikumDb::class.java,
                        "praktikum.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}