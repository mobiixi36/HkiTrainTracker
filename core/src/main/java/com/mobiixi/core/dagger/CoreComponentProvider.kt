package com.mobiixi.core.dagger

import com.mobiixi.core.dagger.CoreComponent

interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}