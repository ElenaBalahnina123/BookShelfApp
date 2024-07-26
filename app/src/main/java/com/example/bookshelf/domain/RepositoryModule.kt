package com.example.bookshelf.domain

import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule{
    @Binds
    abstract fun bindRepository(repositoryImpl: BookShelfRepositoryImpl) : BookShelfRepository
}