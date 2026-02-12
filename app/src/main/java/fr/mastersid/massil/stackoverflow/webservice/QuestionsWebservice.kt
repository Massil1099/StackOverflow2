package fr.mastersid.massil.stackoverflow.webservice

import fr.mastersid.massil.stackoverflow.data.QuestionsApiResponse
import retrofit2.http.GET

interface QuestionsWebservice {

    @GET("questions?pagesize=20&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(): QuestionsApiResponse
}
