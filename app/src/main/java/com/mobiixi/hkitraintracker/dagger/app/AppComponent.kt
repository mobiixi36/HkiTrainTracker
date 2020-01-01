package com.mobiixi.hkitraintracker.dagger.app

import com.mobiixi.core.dagger.CoreComponent
import com.mobiixi.core.dagger.scopes.AppScope
import com.mobiixi.hkitraintracker.MyApplication
import com.mobiixi.hkitraintracker.ui.trainlist.TrainListFragment
import dagger.Component

@Component(modules = [AppModule::class], dependencies = [CoreComponent::class])
@AppScope
interface AppComponent {
    fun inject(application: MyApplication)
    fun inject(fragment: TrainListFragment)
}