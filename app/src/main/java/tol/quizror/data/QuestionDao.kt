package tol.quizror.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionDao {
//    @Query("SELECT * FROM QuizSign WHERE idSign LIKE 1")
    @Query("SELECT * FROM QuizSign ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomQuestion(): QuizSign
    @Query("SELECT idSign " +
            "FROM Sign " +
            "WHERE (subcategorySign = (SELECT subcategorySign FROM Sign WHERE idSign = :idImage) OR " +
            "subcategorySign = (SELECT subcategorySign FROM Sign WHERE idSign = :idImage))")
    suspend fun getSuitableSigns (idImage: Int): List<Int>

}