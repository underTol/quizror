package tol.quizror.data

import android.util.Log
import javax.inject.Inject

class QuizRepository @Inject constructor(private val questionDao: QuestionDao) {
    suspend fun getQuestion(): String {
        var question: String
        var sign: Int
        try {
            val quizSign = questionDao.getRandomQuestion()
            question = quizSign.question
            sign = quizSign.idSign
        } catch (e: Exception) {
            Log.e("MyLog", "Ошибка:", e)
            question = "Репозиторий: ошибка вызова questionDao.getRandomQuestion"
            sign = 0
        }
        return question
    }
}