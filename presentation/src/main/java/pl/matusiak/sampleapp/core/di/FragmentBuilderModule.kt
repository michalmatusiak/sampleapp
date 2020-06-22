package pl.matusiak.sampleapp.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.matusiak.sampleapp.dashboard.DashboardFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun bindDashboardFragment(): DashboardFragment
}