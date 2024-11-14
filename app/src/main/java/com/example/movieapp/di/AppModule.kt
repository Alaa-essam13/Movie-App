package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.App
import com.example.movieapp.data.local.Movie.MovieDatabase
import com.example.movieapp.data.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client:OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
           .baseUrl(MovieApi.BASE_URL)
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "Movie.db"
        ).build()
    }

}