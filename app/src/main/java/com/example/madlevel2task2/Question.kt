package com.example.madlevel2task2

data class Question(
    var questionText: String,
    var questionAnswer: Boolean)
{
    companion object{
        val QUESTIONS = arrayOf(
            "I like this course.",
            "This is quite easy",
            "If a fish could fish, it would fish",
            "Red is blue"
        )

        val ANSWERS = arrayOf(
            true,
            false,
            true,
            false
        )
    }
}