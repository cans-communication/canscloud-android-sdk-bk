package com.cans.canscloud_android_sdk

import android.content.Context
import android.widget.Toast

object CansCenter {
    fun exToast(context: Context, text : String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

}