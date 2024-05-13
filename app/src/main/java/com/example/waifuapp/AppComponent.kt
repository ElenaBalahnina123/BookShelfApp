package com.example.waifuapp

import com.example.waifuapp.network.NetworkModule
import com.example.waifuapp.repository.RepositoryModule

import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)


}



@Module(includes = [NetworkModule::class, RepositoryModule::class])
class AppModule() {

}