package com.anant.quiz.util

import java.util.*

class GlobalVariable {
    companion object {
        const val BASE_URL ="https://opentdb.com"
        var CATEGORY: Int = 9
        var DIFFICULTY: String = "easy"
        var RESULT = ArrayList(Collections.nCopies(10, 0))
    }
}