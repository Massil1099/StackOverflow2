package fr.mastersid.massil.stackoverflow.data

import com.squareup.moshi.Json
import fr.mastersid.massil.stackoverflow.db.Question

data class QuestionsApiResponse(
    @Json(name = "items") val items: List<Question>
)
