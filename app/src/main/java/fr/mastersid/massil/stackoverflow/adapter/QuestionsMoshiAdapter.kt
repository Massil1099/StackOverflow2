package fr.mastersid.massil.stackoverflow.adapter

import com.squareup.moshi.FromJson
import fr.mastersid.massil.stackoverflow.db.Question
import fr.mastersid.massil.stackoverflow.data.QuestionsApiResponse

class QuestionsMoshiAdapter {
    @FromJson
    fun fromJson(questionsApiResponse: QuestionsApiResponse): List<Question> {
        return questionsApiResponse.items
    }
}
