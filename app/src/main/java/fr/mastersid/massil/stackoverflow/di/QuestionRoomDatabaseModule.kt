package fr.mastersid.massil.stackoverflow.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.mastersid.massil.stackoverflow.db.QuestionDao
import fr.mastersid.massil.stackoverflow.db.QuestionRoomDatabase
import jakarta.inject.Singleton

@InstallIn( SingletonComponent:: class )
@Module
object QuestionRoomDatabaseModule {
    @Provides
    fun provideQuestionDao ( questionRoomDatabase : QuestionRoomDatabase) : QuestionDao {
        return questionRoomDatabase.questionDao ()
    }

    @Provides
    @Singleton
    fun provideQuestionRoomDatabase ( @ApplicationContext appContext : Context) : QuestionRoomDatabase {
        return Room.databaseBuilder (
            appContext ,
            QuestionRoomDatabase :: class.java ,
            " weather_database "
        ).build()
    }
}
