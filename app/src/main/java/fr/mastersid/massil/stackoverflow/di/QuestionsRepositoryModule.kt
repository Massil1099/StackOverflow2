package fr.mastersid.massil.stackoverflow.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import fr.mastersid.massil.stackoverflow.repository.QuestionsRepository
import fr.mastersid.massil.stackoverflow.repository.QuestionsRepositoryImpl
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
abstract class QuestionsRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindQuestionsRepository(questionsRepositoryImpl: QuestionsRepositoryImpl):
            QuestionsRepository
    }