package com.example.waifuapp

import com.example.waifuapp.domain.NetworkModule
import com.example.waifuapp.domain.RepositoryModule

import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)


}



@Module(includes = [NetworkModule::class, RepositoryModule::class])
class AppModule() {

}