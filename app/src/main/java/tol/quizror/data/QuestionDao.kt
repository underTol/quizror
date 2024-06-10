package tol.quizror.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionDao {
//    @Query("SELECT * FROM QuizSign WHERE idSign LIKE 1")
    @Query("SELECT * FROM QuizSign ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomQuestion(): QuizSign

}