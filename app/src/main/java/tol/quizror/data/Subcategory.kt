package tol.quizror.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val subcategorySign: String,
    val countSigns: Int
)
