package com.mobiixi.hkitraintracker.dagger.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobiixi.core.dagger.scopes.AppScope
import javax.inject.Inject
import javax.inject.Provider

/**
 * It injects the provider of ViewModel which is provided by Dagger
 */

@AppScope
class MyViewModelFactory<VM : ViewModel> @Inject constructor(
    private val viewModelProvider: @JvmSuppressWildcards Provider<VM>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModelProvider.get() as T
}