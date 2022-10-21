package com.example.jetpackmvvmjing.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.jetpackmvvmjing.R
import com.example.jetpackmvvmjing.app.appViewModel
import com.example.jetpackmvvmjing.app.ext.init
import com.example.jetpackmvvmjing.app.ext.initMain
import com.example.jetpackmvvmjing.app.ext.interceptLongClick
import com.example.jetpackmvvmjing.app.ext.setUiTheme
import com.example.jetpackmvvmjing.databinding.FragmentMainBinding
import com.example.jetpackmvvmjing.viewmodel.state.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
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
        setBadge()
    }

    override fun createObserver() {
        appViewModel.appColor.observeInFragment(this, Observer {
            setUiTheme(it, mainBottom)
        })
    }

    /**
     * 给BottomNavigationView 设置Badge 小红点
     * BottomNavigationMenuView中的每一个Tab是一个FrameLayout，所以可以在上面随意添加View、这样就可以实现角标了
     */
    private fun setBadge() {
        //获取底部菜单view
        val menuView = mainBottom.getChildAt(0) as BottomNavigationMenuView
        //获取第2个itemView
        val itemView = menuView.getChildAt(1) as BottomNavigationItemView
        //引入badgeView
        val badgeView = LayoutInflater.from(this.context).inflate(R.layout.layout_badge_view, menuView, false)
        //把badgeView添加到itemView中
        itemView.addView(badgeView)
        //获取子view并设置显示数目
        val count = badgeView.findViewById<TextView>(R.id.tv_badge)
        count.text = "2"

        //不显示则隐藏
//        count.visibility= View.GONE
    }

}