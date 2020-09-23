package com.example.madlevel2task2

data class Question(
    var questionText: String
)
{
    companion object{
        val QUESTIONS = arrayOf(
            "I like this course.",
            "This is quite easy",
            "If a fish could fish, it would fish",
            "Red is blue"
        )
    }
}