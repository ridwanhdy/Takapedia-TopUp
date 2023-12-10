package memazeria.takapediatopup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diamonds")
data class Diamond(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val count: Int,
    val price: Int
)
