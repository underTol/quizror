package tol.quizror

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import tol.quizror.ui.theme.QuizrorTheme

//Quiz "Rules of the Road" - Викторина "Правила дорожного движения"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizrorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizrorTheme {
        Greeting("Android")
    }
}

@Database(entities = [TestEntity::class] , version = 1, exportSchema = false)
abstract class TestDatabase: RoomDatabase() {

    abstract fun testDao(): TestDao
}

@Dao
interface TestDao {

    @Query("SELECT * FROM test_entity")
    fun testFunAll(): TestEntity
}

@Entity(tableName = "test_entity")
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val testId: Int,
    @ColumnInfo(name = "test_name" ) val testName: String
)