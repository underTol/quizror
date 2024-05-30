package tol.quizror.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategory(
    @PrimaryKey
    val id: Int,
    val subcategorySign: String,
    val countSigns: Int
)
