package com.oys.logak.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import com.oys.logak.base.BaseFragment
import com.oys.logak.databinding.FragmentDashboardBinding

class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater,container,false)
    }



}