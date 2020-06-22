package pl.matusiak.sampleapp.core.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pl.matusiak.domain.di.DomainModule
import pl.matusiak.newdata.di.DataStorageModule
import pl.matusiak.newdata.di.NetworkModule
import pl.matusiak.sampleapp.TestApp
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        DomainModule::class,
        AndroidInjectionModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class,
        DataStorageModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: TestApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}