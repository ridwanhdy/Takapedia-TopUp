package memazeria.takapediatopup.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import memazeria.takapediatopup.data.entity.FfDiamond
import memazeria.takapediatopup.data.entity.PubgDiamond

@Dao
interface PubgDiamondDao {
    @Query("SELECT * FROM pubg_diamonds")
    fun getAllPubgDiamonds(): List<PubgDiamond>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPubgDiamond(diamond: PubgDiamond)

    @Delete
    fun deletePubgDiamond(diamond: PubgDiamond)
}