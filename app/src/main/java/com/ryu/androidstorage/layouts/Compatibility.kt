package com.ryu.androidstorage.layouts

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class Compatibility {
    @RequiresApi(Build.VERSION_CODES.S)
    fun noti(){

    }

    @TargetApi(Build.VERSION_CODES.S)
    fun noti2(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){

        }
    }


}