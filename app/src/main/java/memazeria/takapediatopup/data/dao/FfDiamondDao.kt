package memazeria.takapediatopup.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import memazeria.takapediatopup.data.entity.FfDiamond

@Dao
interface FfDiamondDao {
    @Query("SELECT * FROM ff_diamonds")
    fun getAllFfDiamonds(): List<FfDiamond>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFfDiamond(diamond: FfDiamond)

    @Delete
    fun deleteFfDiamond(diamond: FfDiamond)
}
