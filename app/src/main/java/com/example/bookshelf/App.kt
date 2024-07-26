package com.example.bookshelf

import android.app.Application

class App : Application() {

    val appComponent = DaggerAppComponent.create()


}