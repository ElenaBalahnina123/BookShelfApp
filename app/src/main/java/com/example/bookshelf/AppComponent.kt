package com.example.bookshelf

import com.example.bookshelf.domain.NetworkModule
import com.example.bookshelf.domain.RepositoryModule

import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}



@Module(includes = [NetworkModule::class, RepositoryModule::class])
class AppModule() {

}