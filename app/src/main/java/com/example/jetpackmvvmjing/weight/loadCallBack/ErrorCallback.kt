package com.example.jetpackmvvmjing.app.weight.loadCallBack

import com.example.jetpackmvvmjing.R
import com.kingja.loadsir.callback.Callback

class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}