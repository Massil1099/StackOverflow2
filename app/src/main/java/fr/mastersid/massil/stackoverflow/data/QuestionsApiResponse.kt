package fr.mastersid.massil.stackoverflow.data

import com.squareup.moshi.Json

data class QuestionsApiResponse(
    @Json(name = "items") val items: List<Question>
)
