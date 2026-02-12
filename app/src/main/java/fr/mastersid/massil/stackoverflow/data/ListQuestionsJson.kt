package fr.mastersid.massil.stackoverflow.data

data class ListQuestionsJson(
    val items: List<QuestionJson>
)
data class QuestionJson (
    val id: Int ,
    val title : String ,
    val body : String? = null
)

