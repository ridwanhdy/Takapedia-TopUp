package memazeria.takapediatopup.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import memazeria.takapediatopup.data.dao.DiamondDao
import memazeria.takapediatopup.data.entity.Diamond
import java.time.Instant

@Database(entities = [Diamond::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun DiamondDao() : DiamondDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context): AppDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){

                return tempInstance

            }
            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "diamond-db"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}
