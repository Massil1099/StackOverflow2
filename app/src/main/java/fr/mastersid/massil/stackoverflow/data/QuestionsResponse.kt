package fr.mastersid.massil.stackoverflow.data

import fr.mastersid.massil.stackoverflow.db.Question


sealed interface QuestionsResponse {
    data object Pending : QuestionsResponse

    @JvmInline
    value class Success(val list: List<Question>): QuestionsResponse

    //nouveau etat: etat d'erreur avec un message
    @JvmInline
    value class Error(val message: String): QuestionsResponse

}