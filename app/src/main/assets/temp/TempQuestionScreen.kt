package temp

@Composable
fun QuestionScreen(viewModel: QuestionViewModel = hiltViewModel()) {
    val question by viewModel.question.observeAsState()

    question?.let { currentQuestion ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currentQuestion.questionText, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(16.dp))

            val images = listOf(
                currentQuestion.image1,
                currentQuestion.image2,
                currentQuestion.image3,
                currentQuestion.image4
            ).shuffled()

            images.forEach { image ->
                Image(
                    painter = painterResource(id = getImageResId(image)),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            if (image == currentQuestion.correctImage) {
                                // Handle correct answer
                                Toast.makeText(LocalContext.current, "Correct!", Toast.LENGTH_SHORT).show()
                            } else {
                                // Handle incorrect answer
                                Toast.makeText(LocalContext.current, "Try Again!", Toast.LENGTH_SHORT).show()
                            }
                            viewModel.loadRandomQuestion()
                        }
                )
            }
        }
    }
}

fun getImageResId(imageName: String): Int {
    return when (imageName) {
        "image1" -> R.drawable.image1
        "image2" -> R.drawable.image2
        "image3" -> R.drawable.image3
        "image4" -> R.drawable.image4
        else -> R.drawable.default_image
    }
}
