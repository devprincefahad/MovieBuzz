package dev.prince.moviebuzz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.prince.moviebuzz.data.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase  : RoomDatabase(){

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movieDB"
                ).build()

                INSTANCE = instance

                instance

            }
        }
    }

}