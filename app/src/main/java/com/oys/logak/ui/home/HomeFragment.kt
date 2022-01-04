package com.oys.logak.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.oys.logak.base.BaseFragment
import com.oys.logak.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater,container,false)
    }

    override fun initClickListener() {

    }





}