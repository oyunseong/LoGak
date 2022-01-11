package com.oys.logak.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import com.oys.logak.R
import com.oys.logak.base.BaseFragment
import com.oys.logak.databinding.FragmentHomeBinding
import com.oys.logak.extensions.log
import com.oys.logak.ui.ImprintingModel

/**
 * 1. 증가 각인 선택했을 때 각인 이름과 비어있는 보석 15개가 화면 상단에 보여짐
 * 2. 증가각인 최대 7개 설정 가능
 * 3.
 */

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    val setTestBtn = mutableSetOf<ImprintingModel>()

    private val imprintingArray by lazy {
        arrayOf(
            binding.homeImprinting1,
            binding.homeImprinting2,
            binding.homeImprinting3,
            binding.homeImprinting4,
            binding.homeIncreasingImprinting1,
            binding.homeIncreasingImprinting2,
            binding.homeIncreasingImprinting3,
            binding.homeIncreasingImprinting4,
            binding.homeIncreasingImprinting5,
            binding.homeIncreasingImprinting6,
            binding.homeIncreasingImprinting7,
        )
    }

    val increasingImprintingArray by lazy {
        arrayOf<Spinner>(
            binding.necklaceIncreasingSpinner1,
            binding.necklaceIncreasingSpinner2,
            binding.necklaceDecreasingSpinner,

            binding.earringIncreasingSpinner1,
            binding.earringIncreasingSpinner2,
            binding.earringDecreasingSpinner,

            binding.earring2IncreasingSpinner1,
            binding.earring2IncreasingSpinner2,
            binding.earring2DecreasingSpinner,

            binding.ring1IncreasingSpinner1,
            binding.ring1IncreasingSpinner2,
            binding.ring1DecreasingSpinner,

            binding.ring2IncreasingSpinner1,
            binding.ring2IncreasingSpinner2,
            binding.ring2DecreasingSpinner,

            binding.abilityIncreasingSpinner1,
            binding.abilityIncreasingSpinner2,
            binding.abilityDecreasingSpinner,

            binding.gakinBookIncreasingSpinner1,
            binding.gakinBookIncreasingSpinner2,
        )
    }

    val decreasingImprintingArray by lazy {
        arrayOf<Spinner>(


        )
    }

    val scoreSpinnerArray by lazy {
        arrayOf<Spinner>(
            binding.necklaceEffectNumberSpinner1,
            binding.necklaceEffectNumberSpinner2,
            binding.necklaceEffectNumberSpinner3,

            binding.earringEffectNumberSpinner1,
            binding.earringEffectNumberSpinner2,
            binding.earringEffectNumberSpinner3,

            binding.earring2EffectNumberSpinner1,
            binding.earring2EffectNumberSpinner2,
            binding.earring2EffectNumberSpinner3,

            binding.ring1EffectNumberSpinner1,
            binding.ring1EffectNumberSpinner2,
            binding.ring1EffectNumberSpinner3,

            binding.ring2EffectNumberSpinner1,
            binding.ring2EffectNumberSpinner2,
            binding.ring2EffectNumberSpinner3,

            binding.abilityEffectNumberSpinner1,
            binding.abilityEffectNumberSpinner2,
            binding.abilityEffectNumberSpinner3,

            binding.gakinBookEffectNumberSpinner1,
            binding.gakinBookEffectNumberSpinner2,
        )
    }

    val homeViewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imprintingArray.forEachIndexed { index, imprintingView ->
            imprintingView.visibility = View.GONE
        }

        setupSpinner()

        homeViewModel.imprintingModels.observe(viewLifecycleOwner, {
            "ui model observe $it".log()

        })

        binding.testBtn.setOnClickListener {
            showToast("" + setTestBtn)
            binding.homeImprinting1.visibility = View.VISIBLE
            binding.homeImprinting1.setText(setTestBtn.toString(), "", "")
        }
    }

    private fun setupSpinner() {
        val increasingArray = resources.getStringArray(R.array.increasing_imprinting)
        val decreasingArray = resources.getStringArray(R.array.decreasing_imprinting)
        val accEffectNumberArray = resources.getStringArray(R.array.acc_effect_number)
        val stoneEffectNumberArray = resources.getStringArray(R.array.ability_effect_number)
        val gakinBookEffectNumberArray = resources.getStringArray(R.array.gakin_effect_number)

        // 증가 각인 스피너 부분
        increasingImprintingArray.forEachIndexed { index, spinner ->
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // 선택을 하는 순간
                    val set = mutableSetOf<ImprintingModel>()

                    when(position){
                        0 -> return
                    }
                    for (i in 0..increasingImprintingArray.size) {
                        "증가 각인 position${position}: " + increasingImprintingArray[position].selectedItem.toString()
                            .log()
                        "감소 각인 position${position} : ${scoreSpinnerArray[position].selectedItemId.toInt()}".log()

                        setTestBtn.add(
                            ImprintingModel(
                                text = increasingImprintingArray[position].selectedItem.toString(),
                                score = scoreSpinnerArray[position].selectedItemId.toInt()
                            )
                        )

                        set.add(
                            ImprintingModel(
                                text = "", //itemSpinnerArray[i].selectedItem.toString(),
//                                score = scoreSpinnerArray[i].selectedItem.toString().toInt()
//                                text = "test",
                                score = 1
                            )
                        )
                    }
                    homeViewModel.setUiModel(set)
                }
            }
        }

        binding.necklaceIncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.necklaceIncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.necklaceDecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.earringIncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.earringIncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.earringDecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.earring2IncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.earring2IncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.earring2DecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.ring1IncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.ring1IncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.ring1DecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.ring2IncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.ring2IncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.ring2DecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.abilityIncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.abilityIncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)
        binding.abilityDecreasingSpinner.adapter = getSpinnerAdapter(decreasingArray)

        binding.gakinBookIncreasingSpinner1.adapter = getSpinnerAdapter(increasingArray)
        binding.gakinBookIncreasingSpinner2.adapter = getSpinnerAdapter(increasingArray)


        scoreSpinnerArray.forEachIndexed { index, spinner ->
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    //TODO("Not yet implemented")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    "onItemSelected position : $position id: $id ${scoreSpinnerArray[index].selectedItem}".log()
                    val set = mutableSetOf<ImprintingModel>()
                    for (i in 0..scoreSpinnerArray.size) {
                        set.add(
                            ImprintingModel(
//                                text = itemSpinnerArray[i].selectedItem.toString(),
//                                score = scoreSpinnerArray[i].selectedItem.toString().toInt()
                                text = "test2",
                                score = 0
                            )
                        )
                    }
                    homeViewModel.setUiModel(set)
                }
            }

            spinner.adapter = getSpinnerAdapter(accEffectNumberArray)

            binding.abilityEffectNumberSpinner1.adapter = getSpinnerAdapter(stoneEffectNumberArray)
            binding.abilityEffectNumberSpinner2.adapter = getSpinnerAdapter(stoneEffectNumberArray)
            binding.abilityEffectNumberSpinner3.adapter = getSpinnerAdapter(stoneEffectNumberArray)

            binding.gakinBookEffectNumberSpinner1.adapter =
                getSpinnerAdapter(gakinBookEffectNumberArray)
            binding.gakinBookEffectNumberSpinner2.adapter =
                getSpinnerAdapter(gakinBookEffectNumberArray)
        }

    }

    private fun getSpinnerAdapter(spinnerArray: Array<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            spinnerArray
        )
    }
}