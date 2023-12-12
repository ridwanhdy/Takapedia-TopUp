package memazeria.takapediatopup.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import memazeria.takapediatopup.data.dao.DiamondDao
import memazeria.takapediatopup.data.dao.FfDiamondDao
import memazeria.takapediatopup.data.dao.GensinDiamondDao
import memazeria.takapediatopup.data.dao.PubgDiamondDao
import memazeria.takapediatopup.data.entity.Diamond
import memazeria.takapediatopup.data.entity.FfDiamond
import memazeria.takapediatopup.data.entity.GensinDiamond
import memazeria.takapediatopup.data.entity.PubgDiamond
import java.time.Instant

@Database(entities = [Diamond::class, FfDiamond::class, GensinDiamond::class, PubgDiamond::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun DiamondDao() : DiamondDao
    abstract  fun FfDiamondDao() : FfDiamondDao

    abstract  fun PubgDiamondDao() : PubgDiamondDao
    abstract fun GensinDiamondDao(): GensinDiamondDao

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
