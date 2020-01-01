package com.mobiixi.core.network

import com.mobiixi.core.dagger.scopes.CoreScope
import retrofit2.Retrofit
import javax.inject.Inject

@CoreScope
class MyRestClient@Inject constructor() {
    @Inject
    lateinit var  retrofit: Retrofit

    fun <T> getService(endPoint: Class<T>): T {
        return retrofit.create(endPoint)
    }
}