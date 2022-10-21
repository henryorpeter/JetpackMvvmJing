package com.example.jetpackmvvmjing.ui.fragment.home

import android.os.Bundle
import com.example.jetpackmvvmjing.app.appViewModel
import com.example.jetpackmvvmjing.app.ext.setUiTheme
import com.example.jetpackmvvmjing.databinding.MeFragmentBinding
import com.example.jetpackmvvmjing.viewmodel.state.MeViewModel
import kotlinx.android.synthetic.main.me_fragment.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment
import me.hgj.jetpackmvvm.demo.app.ext.jumpByLogin
import me.hgj.jetpackmvvm.ext.nav

/**
 * 我的页面
 */
class MeFragment : BaseFragment<MeViewModel, MeFragmentBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.vm = mViewModel
        mDatabind.click = ProxyClick()
        appViewModel.appColor.value?.let { setUiTheme(it, me_linear) }
    }

    inner class ProxyClick {
        /** 登NM录 */
        fun login() {
            nav().jumpByLogin {}
        }
    }
}