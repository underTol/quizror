package temp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tol.quizror.ui.QuizState

@Composable
fun QuizApp(viewModel: QuizViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value

    QuizScreen(
        state = state,
        onImageSelected = viewModel::selectImage,
        onNextQuestion = viewModel::loadNewQuestion
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    state: QuizState,
    onImageSelected: (ImageOption) -> Unit,
    onNextQuestion: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Quiz Game") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.question?.let {
                Text(
                    text = it.questionText,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
            }

            state.images.forEach { imageOption ->
                val selected = state.selectedImage == imageOption
                val correct = state.correct == true && selected
                val incorrect = state.correct == false && selected
                val color = when {
                    correct -> Color.Green
                    incorrect -> Color.Red
                    else -> Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, color, CircleShape)
                        .padding(8.dp)
                        .clickable { onImageSelected(imageOption) }
                ) {
                    Image(
                        painter = painterResource(id = getDrawableResourceId(imageOption.imageName)),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }

            state.correct?.let {
                Text(
                    text = if (it) "Correct!" else "Incorrect!",
                    color = if (it) Color.Green else Color.Red,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            if (state.correct != null) {
                Button(onClick = onNextQuestion) {
                    Text("Next Question")
                }
            }
        }
    }
}

@Composable
fun getDrawableResourceId(imageName: String): Int {
    return when (imageName) {
        "image1" -> R.drawable.image1
        "image2" -> R.drawable.image2
        "image3" -> R.drawable.image3
        "image4" -> R.drawable.image4
        else -> R.drawable.placeholder // Placeholder - изображение по умолчанию
    }
}
