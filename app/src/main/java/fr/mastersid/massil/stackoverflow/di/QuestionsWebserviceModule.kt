package fr.mastersid.massil.stackoverflow.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.massil.stackoverflow.webservice.QuestionsWebservice
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.stackexchange.com/2.3/"

@Module
@InstallIn(SingletonComponent::class)
object QuestionsWebserviceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestionsWebservice(retrofit: Retrofit): QuestionsWebservice {
        return retrofit.create(QuestionsWebservice::class.java)
    }
}