package memazeria.takapediatopup.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import memazeria.takapediatopup.data.entity.Diamond

@Dao
interface DiamondDao {
    @Query("SELECT * FROM diamonds")
    fun getAllDiamonds(): List<Diamond>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiamond(diamond: Diamond)

    @Delete
    fun deleteDiamond(diamond: Diamond)
}
