package com.example.waifuapp.repository

import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule{
    @Binds
    abstract fun bindRepository(repositoryImpl: WaifuRepositoryImpl) : WaifuRepository
}