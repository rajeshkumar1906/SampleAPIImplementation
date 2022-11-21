package com.rajeshkumar.sampleapiimplementation.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule @Inject constructor(private val onlineApi: OnlineApi){
     fun getData() = onlineApi.getData()
}