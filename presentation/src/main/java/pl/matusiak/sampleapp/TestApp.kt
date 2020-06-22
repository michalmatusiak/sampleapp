package pl.matusiak.sampleapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.matusiak.sampleapp.core.di.ApplicationComponent
import pl.matusiak.sampleapp.core.di.DaggerApplicationComponent

class TestApp : DaggerApplication() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}