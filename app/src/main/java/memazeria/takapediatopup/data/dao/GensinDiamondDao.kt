package memazeria.takapediatopup.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import memazeria.takapediatopup.data.entity.GensinDiamond

@Dao
interface GensinDiamondDao {
    @Query("SELECT * FROM gensin_diamonds")
    fun getAllFfDiamonds(): List<GensinDiamond>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFfDiamond(diamond: GensinDiamond)

    @Delete
    fun deleteFfDiamond(diamond: GensinDiamond)
}