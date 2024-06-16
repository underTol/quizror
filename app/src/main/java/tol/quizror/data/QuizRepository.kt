package tol.quizror.data

import android.util.Log
import javax.inject.Inject

class QuizRepository @Inject constructor(private val questionDao: QuestionDao) {
    suspend fun getQuestion(): Pair<String, Int> {
        var question: String
        var sign: Int = 0
        try {
            val quizSign = questionDao.getRandomQuestion()
            question = quizSign.question
            sign = quizSign.idSign
        } catch (e: Exception) {
            Log.e("MyLog", "Ошибка: вызов questionDao.getRandomQuestion", e)
            question = "QuizRepository.getQuestion: ошибка вызова questionDao.getRandomQuestion"
        }
        return question to sign
    }

    suspend fun getIdSigns(idImage: Int): List<Int> {
        var signs: List<Int> = emptyList()
        try {
            val signs = questionDao.getSuitableSigns(idImage)
        } catch (e: Exception) {
            Log.e("MyLog", "Ошибка: в QuizRepository.getIdSigns вызов questionDao.getSuitableSigns", e)
        }
        return signs
    }
}