package pl.matusiak.sampleapp.core.di

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulersProvider @Inject constructor() {

    fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()!!

    fun getBackgroundThread(): Scheduler = Schedulers.io()
}