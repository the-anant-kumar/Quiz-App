package com.anant.quiz.models

data class QuestionResponse(
    val response_code: Int,
    val results: List<Result>
)