package com.oys.logak.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.oys.logak.R
import com.oys.logak.base.BaseFragment
import com.oys.logak.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun initClickListener() {
        setSpinner()
    }


    private fun setSpinner() {
        val increasingArray = resources.getStringArray(R.array.increasing_imprinting)
        val decreasingArray = resources.getStringArray(R.array.decreasing_imprinting)
        val accEffectNumberArray = resources.getStringArray(R.array.acc_effect_number)
        val abilityStoneArray = resources.getStringArray(R.array.ability_effect_number)

        val increasingAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            increasingArray
        )
        val decreasingAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            decreasingArray
        )
        val accEffectNumberAdapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            accEffectNumberArray
        )

        binding.necklaceIncreasingSpinner1.adapter = increasingAdapter
        binding.necklaceIncreasingSpinner2.adapter = increasingAdapter
        binding.necklaceDecreasingSpinner.adapter = decreasingAdapter
        binding.necklaceEffectNumberSpinner1.adapter = accEffectNumberAdapter
        binding.necklaceEffectNumberSpinner2.adapter = accEffectNumberAdapter
        binding.necklaceEffectNumberSpinner3.adapter = accEffectNumberAdapter

        binding.necklaceIncreasingSpinner1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {

                        }
                        1 -> {
                            showToast( "${position}번 째 각인 입니다.")
                        }
                    }
                }
            }

        binding.necklaceIncreasingSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        showToast( "${position}번 째 각인 입니다.")
                    }
                }
            }
        }

        binding.necklaceDecreasingSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        showToast( "${position}번 째 각인 입니다.")
                    }
                }
            }
        }
        binding.necklaceEffectNumberSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        showToast( "+${position}")
                    }
                }
            }
        }
        binding.necklaceEffectNumberSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        showToast( "+${position}")
                    }
                }
            }
        }
        binding.necklaceEffectNumberSpinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        showToast( "+${position}")
                    }
                }
            }
        }
    }
}