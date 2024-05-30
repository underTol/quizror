package tol.quizror.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [QuizSign::class, Sign::class, Subcategory::class],
    version = 1
)
abstract class QrorDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}