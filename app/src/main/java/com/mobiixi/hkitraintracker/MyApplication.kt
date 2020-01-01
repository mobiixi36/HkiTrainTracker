package com.mobiixi.hkitraintracker

import android.app.Application
import com.mobiixi.core.dagger.CoreComponent
import com.mobiixi.core.dagger.CoreComponentProvider
import com.mobiixi.core.dagger.DaggerCoreComponent
import com.mobiixi.core.dagger.modules.ContextModule
import com.mobiixi.hkitraintracker.dagger.CoreComponentInjector
import com.mobiixi.hkitraintracker.dagger.app.AppComponent
import com.mobiixi.hkitraintracker.dagger.app.DaggerAppComponent

class MyApplication : Application(), CoreComponentProvider {

    private lateinit var coreComponent: CoreComponent
    lateinit var appComponent: AppComponent

    override fun provideCoreComponent(): CoreComponent {
        if(!this::coreComponent.isInitialized) {
            val myContextModule = ContextModule(applicationContext)
            coreComponent = DaggerCoreComponent.builder().contextModule(myContextModule).build()
        }

        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                                        .coreComponent(CoreComponentInjector.injectCoreComponent(applicationContext))
                                        .build()

        appComponent.inject(this)

    }

}