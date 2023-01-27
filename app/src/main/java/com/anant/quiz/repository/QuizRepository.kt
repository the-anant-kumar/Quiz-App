package com.anant.quiz.repository

import com.anant.quiz.api.RetrofitInstance

class QuizRepository {

    suspend fun getQuestions(category: Int, difficulty: String) =
        RetrofitInstance.api.getQuestions(category, difficulty)
}