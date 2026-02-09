package fr.mastersid.massil.stackoverflow.repository

import fr.mastersid.massil.stackoverflow.data.QuestionsResponse
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    val questionsResponse : Flow<QuestionsResponse>
    suspend fun updateQuestionsInfo()
}