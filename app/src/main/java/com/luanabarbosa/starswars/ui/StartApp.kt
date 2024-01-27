package com.luanabarbosa.starswars.ui

import android.app.Application
import com.luanabarbosa.starswars.films.di.filmsModule
import com.luanabarbosa.starswars.people.di.peopleModule
import com.luanabarbosa.starswars.planets.di.planetModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StartApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StartApp)
            modules(listOf(peopleModule, planetModule, filmsModule))
        }
    }
}
