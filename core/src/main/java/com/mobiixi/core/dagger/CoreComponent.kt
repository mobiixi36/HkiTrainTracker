package com.mobiixi.core.dagger

import com.mobiixi.core.dagger.modules.ContextModule
import com.mobiixi.core.dagger.modules.NetworkModule
import com.mobiixi.core.dagger.scopes.CoreScope
import com.mobiixi.core.repository.TrainsRepository
import dagger.Component

@Component(modules = [NetworkModule::class, ContextModule::class])
@CoreScope
interface CoreComponent {
    fun getTrainsRepository(): TrainsRepository
}