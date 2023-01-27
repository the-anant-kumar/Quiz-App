package com.anant.quiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anant.quiz.repository.QuizRepository

class QuizViewModelProviderFactory(
    val quizRepository: QuizRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(quizRepository) as T
    }
}