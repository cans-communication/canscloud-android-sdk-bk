package com.cans.canscloud_android_sdk.singleton

class Singleton private constructor() {

    object Holder {
        val INSTANCE = Singleton()
    }

    companion object {
        val instance: Singleton by lazy { Holder.INSTANCE }
    }
}