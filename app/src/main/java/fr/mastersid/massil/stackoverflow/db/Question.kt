package fr.mastersid.massil.stackoverflow.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName ="question_table" )
data class Question(
                    @PrimaryKey @Json(name = "question_id") val id: Int,
                    val title : String,
                    @Json(name = "answer_count") val answerCount : Int,
                    val body : String? )

