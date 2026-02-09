package fr.mastersid.massil.stackoverflow.data

sealed interface QuestionsResponse {
    data object Pending : QuestionsResponse

    @JvmInline
    value class Success(val list: List<Question>): QuestionsResponse
}