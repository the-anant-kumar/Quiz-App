package com.anant.quiz.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anant.quiz.models.QuestionResponse
import com.anant.quiz.repository.QuizRepository
import com.anant.quiz.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class QuizViewModel(
    private val quizRepository: QuizRepository
) : ViewModel() {

    val questions: MutableLiveData<Resource<QuestionResponse>> = MutableLiveData()

    fun getQuestions(category: Int, difficulty: String) = viewModelScope.launch {
        questions.postValue(Resource.Loading())
        val response = quizRepository.getQuestions(category, difficulty)
        questions.postValue(handleQuestionResponse(response))
    }

    private fun handleQuestionResponse(response: Response<QuestionResponse>) : Resource<QuestionResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}