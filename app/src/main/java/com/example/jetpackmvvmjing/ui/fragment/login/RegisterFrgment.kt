package com.example.jetpackmvvmjing.ui.fragment.login

import android.os.Bundle
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.jetpackmvvmjing.R
import com.example.jetpackmvvmjing.app.appViewModel
import com.example.jetpackmvvmjing.app.ext.initClose
import com.example.jetpackmvvmjing.databinding.FragmentRegisterBinding
import com.example.jetpackmvvmjing.util.CacheUtil
import com.example.jetpackmvvmjing.util.SettingUtil
import com.example.jetpackmvvmjing.viewmodel.request.RequestLoginRegisterViewModel
import com.example.jetpackmvvmjing.viewmodel.state.LoginRegisterViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment
import me.hgj.jetpackmvvm.demo.app.ext.showMessage
import me.hgj.jetpackmvvm.ext.nav
import me.hgj.jetpackmvvm.ext.navigateAction
import me.hgj.jetpackmvvm.ext.parseState

/**
 * 作者　:
 * 时间　: 2019/12/24
 * 描述　:
 */
class RegisterFrgment : BaseFragment<LoginRegisterViewModel, FragmentRegisterBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.viewmodel = mViewModel
        mDatabind.click = ProxyClick()
        toolbar.initClose("注册") {
            nav().navigateUp()
        }
        //设置颜色跟主题颜色一致
        appViewModel.appColor.value?.let {
            SettingUtil.setShapColor(registerSub, it)
            toolbar.setBackgroundColor(it)
        }
    }

    override fun createObserver() {
        requestLoginRegisterViewModel.loginResult.observe(
            viewLifecycleOwner,
            Observer { resultState ->
                parseState(resultState, {
                    CacheUtil.setIsLogin(true)
                    CacheUtil.setUser(it)
                    appViewModel.userInfo.value = it
                    nav().navigateAction(R.id.action_registerFrgment_to_mainFragment)
                }, {
                    showMessage(it.errorMsg)
                })
            })
    }


    inner class ProxyClick {
        /**清空*/
        fun clear() {
            mViewModel.username.set("")
        }

        /**注册*/
        fun register() {
            when {
                mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                mViewModel.password2.get().isEmpty() -> showMessage("请填写确认密码")
                mViewModel.password.get().length < 6 -> showMessage("密码最少6位")
                mViewModel.password.get() != mViewModel.password2.get() -> showMessage("密码不一致")
                else -> requestLoginRegisterViewModel.registerAndlogin(
                    mViewModel.username.get(),
                    mViewModel.password.get()
                )
            }
        }

        var onCheckedChangeListener1 = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            mViewModel.isShowPwd.set(isChecked)
        }
        var onCheckedChangeListener2 = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            mViewModel.isShowPwd2.set(isChecked)
        }
    }
}