package fr.mastersid.massil.stackoverflow.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.mastersid.massil.stackoverflow.repository.QuestionsRepository
import fr.mastersid.massil.stackoverflow.repository.QuestionsRepositoryDummyImpl

@Module
@InstallIn(ViewModelComponent:: class)
abstract class QuestionsRepositoryModule {
    @Binds
    abstract fun bindQuestionsRepository(questionsRepositoryImpl: QuestionsRepositoryDummyImpl):
            QuestionsRepository
    }