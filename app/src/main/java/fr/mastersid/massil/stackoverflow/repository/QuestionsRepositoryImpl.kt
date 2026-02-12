package fr.mastersid.massil.stackoverflow.repository

import android.util.Log
import fr.mastersid.massil.stackoverflow.data.QuestionsResponse
import fr.mastersid.massil.stackoverflow.webservice.QuestionsWebservice
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.IOException
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsWebservice: QuestionsWebservice
) : QuestionsRepository {

    override val questionsResponse = MutableSharedFlow<QuestionsResponse>()

    override suspend fun updateQuestionsInfo() {
        try {
            questionsResponse.emit(QuestionsResponse.Pending)
            val response = questionsWebservice.getQuestions()
            questionsResponse.emit(QuestionsResponse.Success(response.items))
        } catch (e: IOException) {
            Log.d("Webservice", "Error: $e")
        }

    }

}

