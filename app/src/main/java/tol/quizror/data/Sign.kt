package tol.quizror.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sign(
    @PrimaryKey
    val idSign: Int,
    val nameSign: String,
    val categorySign: String,
    val subcategorySign: String,
    val subcategorySign2: String?,
    val numberInRor: String,
)
