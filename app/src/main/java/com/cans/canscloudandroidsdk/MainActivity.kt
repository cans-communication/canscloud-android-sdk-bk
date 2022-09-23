package com.cans.canscloudandroidsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cans.canscloud_android_sdk.CansCenter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CansCenter.exToast(this, "Hello World")
    }
}