package io.github.pengdst.jetpacksubmission.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.pengdst.jetpacksubmission.data.local.room.MovieRoomDatabase
import io.github.pengdst.jetpacksubmission.data.local.source.MovieLocalSource
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.RetrofitBuilder
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.routes.MovieRoute
import io.github.pengdst.jetpacksubmission.data.remote.source.MovieRemoteSource
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() = RetrofitBuilder.build()

    @Provides
    @Singleton
    fun provideMovieRoomDatabase(@ApplicationContext context: Context) =
        MovieRoomDatabase.newInstance(context)

    @Provides
    @Singleton
    fun provideMovieLocalSource(db: MovieRoomDatabase) = MovieLocalSource(db.movieDao())

    @Provides
    @Singleton
    fun provideMovieRemoteSource(retrofit: Retrofit) = MovieRemoteSource(retrofit.create(MovieRoute::class.java))

}