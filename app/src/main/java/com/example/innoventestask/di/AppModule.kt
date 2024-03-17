package com.example.innoventestask.di

import com.example.innoventestask.network.ApiController
import com.example.innoventestask.network.RetrofitClient
import com.example.innoventestask.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiController: ApiController
    ): Repository = Repository(
        apiController = apiController,
    )

    @Provides
    @Singleton
    fun provideApiController(): ApiController =
        RetrofitClient.getRetrofit().create(ApiController::class.java)

}