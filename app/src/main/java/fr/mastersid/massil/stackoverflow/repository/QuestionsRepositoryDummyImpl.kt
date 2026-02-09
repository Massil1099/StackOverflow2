package fr.mastersid.massil.stackoverflow.repository

import fr.mastersid.massil.stackoverflow.data.Question
import fr.mastersid.massil.stackoverflow.data.QuestionsResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlin.random.Random

class QuestionsRepositoryDummyImpl @Inject constructor() : QuestionsRepository {
    override val questionsResponse = MutableSharedFlow<QuestionsResponse>()
    override suspend fun updateQuestionsInfo() {
        questionsResponse.emit(QuestionsResponse.Pending)
        delay(5000)
        val questions = listOf(
            Question(
                1,
                "Change tenantIdentifier at Controller",
                Random.nextInt(0, 10),
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            Question(
                2,
                "Is there a way to use application that",
                Random.nextInt(0, 10),
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
            Question(
                3,
                "Ubuntu 24.04 - System crashes",
                Random.nextInt(0, 10),
                body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"
            ),
        )
        questionsResponse.emit(
            QuestionsResponse.Success(questions)
        )
    }
}
