package io.github.pengdst.jetpacksubmission.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepository
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepositoryImpl
import io.github.pengdst.jetpacksubmission.data.source.remote.source.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.source.remote.RetrofitBuilder
import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute
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
    fun provideMovieRoute(retrofit: Retrofit): MovieRoute = retrofit.create(MovieRoute::class.java)

    @Provides
    @Singleton
    fun provideMovieRemoteSource(route: MovieRoute) = MovieRemoteSource(route)

    @Provides
    @Singleton
    fun provideMovieRepository(remote: MovieRemoteSource): MovieRepository = MovieRepositoryImpl(remote)

}