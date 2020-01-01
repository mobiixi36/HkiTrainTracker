package com.mobiixi.hkitraintracker.dagger.trains

import com.mobiixi.core.dagger.scopes.TrainsScope
import dagger.Component

@Component(modules = [TrainsModule::class])
@TrainsScope
interface TrainsComponent {
}