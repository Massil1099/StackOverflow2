package fr.mastersid.massil.stackoverflow.repository

import android.util.Log
import fr.mastersid.massil.stackoverflow.data.QuestionsResponse
import fr.mastersid.massil.stackoverflow.db.Question
import fr.mastersid.massil.stackoverflow.db.QuestionDao
import fr.mastersid.massil.stackoverflow.webservice.QuestionsWebservice
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import java.io.IOException
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val questionsWebservice: QuestionsWebservice,
    private val questionDao : QuestionDao

    ) : QuestionsRepository {


    private val pendingFlow : MutableSharedFlow < QuestionsResponse > = MutableSharedFlow ()

    override val questionsResponse = listOf (
        pendingFlow ,
        questionDao.getQuestionListFlow().map{list -> QuestionsResponse.Success (list) }
    ).merge()

    override suspend fun updateQuestionsInfo() {
        try {
            pendingFlow.emit(QuestionsResponse.Pending)

            val list = questionsWebservice.getQuestions()

            questionDao.insertAll(list.items)

        } catch (e: IOException) {
            Log.d("Webservice", "Error: $e")
        }


    }

}

