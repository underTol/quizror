package tol.quizror.data

import android.util.Log
import javax.inject.Inject

class QuizRepository @Inject constructor(private val questionDao: QuestionDao) {
    suspend fun getQuestion(): String {
        Log.i("MyLog", "Repo run")
        val question = questionDao.getRandomQuestion()
        val testQuestion = question.question
        Log.i("MyLog", "Repo testQuestion: $testQuestion")
        return testQuestion
    }
}