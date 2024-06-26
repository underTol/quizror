package tol.quizror.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tol.quizror.data.QuizRepository
import javax.inject.Inject

data class QuizState(
    val question: String = "",
    val correctImage: Int? = null,
    val images: List<String> = emptyList(),
    val selectedImage: String? = null,
    val correct: Boolean? = null,
)

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: QuizRepository) : ViewModel() {
    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state

    init {
        loadNewQuestion()
    }

    private fun loadNewQuestion() {
        viewModelScope.launch {
            val (question, correctImage) = repository.getQuestion()
            val idImages = repository.getIdSigns(correctImage)
            val images = idImages.map { "id$it" }
            _state.value = QuizState(question = question, correctImage = correctImage, images = images)

        }
    }

//    fun selectImage(image: ImageOption) {
//        val question = _state.value.question
//        val correct = question?.correctImage == image.imageName
//        _state.value = _state.value.copy(selectedImage = image, correct = correct)
//    }

    // Для UI
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun incrementCounter() {
        viewModelScope.launch {
            _counter.value += 1
        }
    }
}
