package fr.mastersid.massil.stackoverflow.data

import com.squareup.moshi.Json

data class Question(
    @Json(name = "question_id") val id: Int,
    val title: String,
    @Json(name = "answer_count") val answerCount: Int,
    val body: String?
)
