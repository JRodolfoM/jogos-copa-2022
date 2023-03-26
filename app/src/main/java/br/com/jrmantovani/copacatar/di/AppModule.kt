package br.com.jrmantovani.copacatar.di

import android.content.Context
import br.com.jrmantovani.copacatar.data.local.dao.NotificationDAO
import br.com.jrmantovani.copacatar.data.local.database.DataBases
import br.com.jrmantovani.copacatar.data.local.repository.NotificationImpl
import br.com.jrmantovani.copacatar.data.remote.api.MatchAPI
import br.com.jrmantovani.copacatar.data.remote.repository.MatchRepositoryImpl
import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMatch() : MatchAPI {
        return Retrofit.Builder()
            .baseUrl("https://jrodolfom.github.io/jogo-copa-2022-android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatchAPI::class.java)
    }

    @Provides
    fun provideMatchRepository(api: MatchAPI, notificationDAO: NotificationDAO): MatchRepository{
        return MatchRepositoryImpl(api, notificationDAO )
    }



    //Banco de dados
    @Provides
    fun provideNotificationRepository(notificationDAO: NotificationDAO) : NotificationImpl {
        return NotificationImpl(notificationDAO)
    }

    @Provides
    fun provideNotificationDAO(dataBases: DataBases) : NotificationDAO {
        return dataBases.notificationDAO
    }

    @Provides
    fun provideBancoDados(@ApplicationContext context: Context) : DataBases{
        return DataBases.getInstance(context)
    }

}