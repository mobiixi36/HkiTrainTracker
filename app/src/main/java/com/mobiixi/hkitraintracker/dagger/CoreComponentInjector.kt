package com.mobiixi.hkitraintracker.dagger

import android.content.Context
import com.mobiixi.core.dagger.CoreComponent
import com.mobiixi.core.dagger.CoreComponentProvider
import java.lang.RuntimeException

object CoreComponentInjector {
    fun injectCoreComponent(applicationContext: Context): CoreComponent {
        if (applicationContext is CoreComponentProvider) {
            return (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw RuntimeException("Not an application context that conforms to CoreComponentProvider!")
        }
    }
}