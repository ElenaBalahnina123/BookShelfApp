package com.example.waifuapp

import android.app.Application

class App : Application() {

    val appComponent = DaggerAppComponent.create()


}