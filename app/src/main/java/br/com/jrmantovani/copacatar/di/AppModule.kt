package br.com.jrmantovani.copacatar.di

import br.com.jrmantovani.copacatar.data.remote.MatchAPI
import br.com.jrmantovani.copacatar.data.repository.MatchRepositoryImpl
import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMatch() : MatchAPI{
        return Retrofit.Builder()
            .baseUrl("https://digitalinnovationone.github.io/copa-2022-android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatchAPI::class.java)
    }

    @Provides
    fun provideMatchRepository(api: MatchAPI): MatchRepository{
        return MatchRepositoryImpl(api)
    }

}