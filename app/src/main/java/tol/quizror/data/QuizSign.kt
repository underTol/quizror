package tol.quizror.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizSign(
    @PrimaryKey
    val id: Int,
    val question: String,
    val idSign: Int
)
