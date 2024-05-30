package tol.quizror.data

import android.util.Log
import javax.inject.Inject

class QuizRepository @Inject constructor(private val questionDao: QuestionDao) {
    suspend fun getQuestion(): String {
        var testQuestion: String = "Begin"
        Log.i("MyLog", "Repo run")
        try {
            Log.i("MyLog", "Repo run in Try")
            val question = questionDao.getRandomQuestion()
            Log.i("MyLog", "Repo question: $question")
            testQuestion = question.question
            Log.i("MyLog", "Repo testQuestion: $testQuestion")
        } catch (e: Exception) {
//            e.printStackTrace();
            Log.e("MyLog", "Ошибка:", e)
        }


        return testQuestion
    }
}