package com.example.jetpackmvvmjing.ui.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.jetpackmvvmjing.R
import com.example.jetpackmvvmjing.app.appViewModel
import com.example.jetpackmvvmjing.app.ext.init
import com.example.jetpackmvvmjing.app.ext.initMain
import com.example.jetpackmvvmjing.app.ext.interceptLongClick
import com.example.jetpackmvvmjing.app.ext.setUiTheme
import com.example.jetpackmvvmjing.databinding.FragmentMainBinding
import com.example.jetpackmvvmjing.viewmodel.state.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment

/**
 * 描述　:项目主页Fragment
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        //初始化 bottomBar
        mainBottom.init{
            when (it) {
                R.id.menu_main -> mainViewpager.setCurrentItem(0, false)
                R.id.menu_project -> mainViewpager.setCurrentItem(1, false)
//                R.id.menu_system -> mainViewpager.setCurrentItem(2, false)
//                R.id.menu_public -> mainViewpager.setCurrentItem(3, false)
                R.id.menu_me -> mainViewpager.setCurrentItem(2, false)
            }
        }
        mainBottom.interceptLongClick(R.id.menu_main,R.id.menu_project,R.id.menu_me)//拦截BottomNavigation长按事件 防止长按时出现Toast
    }

    override fun createObserver() {
        appViewModel.appColor.observeInFragment(this, Observer {
            setUiTheme(it, mainBottom)
        })
    }

}