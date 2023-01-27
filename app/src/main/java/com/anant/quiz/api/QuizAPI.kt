package com.anant.quiz.api

import com.anant.quiz.models.QuestionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizAPI {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("amount") amount: Int = 10,
        @Query("type") type: String = "multiple"
    ) : Response<QuestionResponse>
}