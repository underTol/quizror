package tol.quizror.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tol.quizror.data.QrorDatabase
import tol.quizror.data.QuestionDao
import tol.quizror.data.QuizRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModul {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): QrorDatabase {
        return Room.databaseBuilder(
            app,
            QrorDatabase::class.java,
            "qror.db"
        ).createFromAsset("db/qror.db").build()
    }

    @Singleton
    @Provides
    fun provideQuestionDao(db: QrorDatabase): QuestionDao {
        return db.questionDao()
    }

    @Singleton
    @Provides
    fun provideQuizRepository(dao: QuestionDao): QuizRepository {
        return QuizRepository(dao)
    }

}