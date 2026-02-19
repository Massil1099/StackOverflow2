package fr.mastersid.massil.stackoverflow.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (list: List <Question>)

    @Query("SELECT * FROM question_table")
    fun getQuestionListFlow () : Flow<List<Question>>
}