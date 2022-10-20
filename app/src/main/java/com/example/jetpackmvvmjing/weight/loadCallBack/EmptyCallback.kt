package com.example.jetpackmvvmjing.app.weight.loadCallBack


import com.example.jetpackmvvmjing.R
import com.kingja.loadsir.callback.Callback

class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}