package com.oys.logak.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.oys.logak.R
import com.oys.logak.base.BaseFragment
import com.oys.logak.databinding.FragmentHomeBinding
import com.oys.logak.extensions.log
import com.oys.logak.model.Imprint
import com.oys.logak.server.GithubAPI
import com.oys.logak.server.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit

/**
 * 1. 증가 각인 선택했을 때 각인 이름과 비어있는 보석 15개가 화면 상단에 보여짐
 * 2. 증가각인 최대 7개 설정 가능
 * 3. 속성 추가 시 app 자체를 업데이트 해야하는 이슈가 있으므로 서버에 item array 를 옮기는 것이 좋다
 * 4. text 가 한글로 하드코딩 되어있기 때문에 다른 언어를 지원할 때 수정해야하는 문제가 있다. ( 한글 -> 영어 번역 등등)
 * 5. 선택할 때마다 map 객체를 계속 생성 중이라 메모리 누수 발생 가능성이 있음
 */

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val map = mutableMapOf<String, Int>()

    lateinit var retrofit: Retrofit
    lateinit var githubAPI: GithubAPI

    // 커스텀뷰 어레이
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
            binding.homeIncreasingImprinting8,
            binding.homeIncreasingImprinting9,
            binding.homeIncreasingImprinting10,
            binding.homeIncreasingImprinting11,
            binding.homeIncreasingImprinting12,
            binding.homeIncreasingImprinting13,
            binding.homeIncreasingImprinting14,
        )
    }

    // 증가 감소 각인 어레이
    private val itemSpinnerArray by lazy {
        arrayOf(
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

    // 숫자 어레이
    private val scoreSpinnerArray by lazy {
        arrayOf(
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

    private val homeViewModel: HomeViewModel by viewModels()

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

        initServer()

        /**
         * 1. 각인 text / text 가 map 에 올라갔을 때 view visible
         * 2. 각인 Level
         * 3. 초과 score
         * 4. score 만큼 보석 박기
         * */

        // 모델의 값이 변경되었을 때 콜백이 불림
        homeViewModel.model.observe(viewLifecycleOwner, {
            "ui model observe $it".log()
            var index = 0
            val animation: Animation = AlphaAnimation(0f, 1f)
            animation.duration = 500;

            it.forEach {
                imprintingArray[index].setImprintingModel(it)
                imprintingArray[index].visibility = View.VISIBLE
                imprintingArray[index].animation = animation
                index++
            }
            // 1. score 만큼 보석 박기
            // 2. 감소, 증가 각인 text 색깔 구분
            // 3. 감소각인은 위에 증가각인은 아래

            for (i in index until imprintingArray.size) {
                imprintingArray[i].visibility = View.GONE
            }
        })
    }

    private fun initServer() {
        retrofit = RetrofitClient.getInstance()
        githubAPI = retrofit.create(GithubAPI::class.java)

        Runnable {
            githubAPI.getImprintingArray()!!.enqueue(object : retrofit2.Callback<Imprint> {
                override fun onResponse(call: Call<Imprint>, response: Response<Imprint>) {
                    val imprint = response.body()
                    if (imprint == null) {
                        //토스트 띄우고 엠티뷰 노출
                        return
                    }
                    "onResponse!".log("initServer")
                    setupSpinner(imprint)
                }

                override fun onFailure(call: Call<Imprint>, t: Throwable) {
                    "onFailure message : " + t.message.toString().log("initServer")
                }

            })
        }.run()
    }


    private fun setupSpinner(imprint: Imprint) {
        // 각인 선택
        itemSpinnerArray.forEachIndexed { _, spinner ->
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    getSelectedSpinnerItem()
                }
            }
        }

        binding.necklaceIncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.necklaceIncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.necklaceDecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.earringIncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.earringIncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.earringDecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.earring2IncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.earring2IncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.earring2DecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.ring1IncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.ring1IncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.ring1DecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.ring2IncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.ring2IncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.ring2DecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.abilityIncreasingSpinner1.adapter = getSpinnerAdapter(imprint.battleImprintList)
        binding.abilityIncreasingSpinner2.adapter = getSpinnerAdapter(imprint.battleImprintList)
        binding.abilityDecreasingSpinner.adapter = getSpinnerAdapter(imprint.decreaseImprintList)

        binding.gakinBookIncreasingSpinner1.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())
        binding.gakinBookIncreasingSpinner2.adapter = getSpinnerAdapter(imprint.getJobAndBattleImprintList())


        // 스코어 스피너 부분
        scoreSpinnerArray.forEachIndexed { _, spinner ->
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
                    getSelectedSpinnerItem()
                }
            }

            spinner.adapter = getSpinnerAdapter(imprint.accScoreList)

            binding.abilityEffectNumberSpinner1.adapter = getSpinnerAdapter(imprint.abilityScoreList)
            binding.abilityEffectNumberSpinner2.adapter = getSpinnerAdapter(imprint.abilityScoreList)
            binding.abilityEffectNumberSpinner3.adapter = getSpinnerAdapter(imprint.abilityScoreList)

            binding.gakinBookEffectNumberSpinner1.adapter =
                getSpinnerAdapter(imprint.bookScoreList)
            binding.gakinBookEffectNumberSpinner2.adapter =
                getSpinnerAdapter(imprint.bookScoreList)
        }
    }

    fun reset() {
        //배열에 담긴 스피너 반복문 돌리면서 클리어 시키기
        //스피너 상태 viewmodel에 반영시키기
    }

    private fun getSelectedSpinnerItem() {
        map.clear()

        for (i in scoreSpinnerArray.indices) {
            val key = itemSpinnerArray[i].selectedItem.toString()
            val value = scoreSpinnerArray[i].selectedItem.toString().toInt()

            if (key == "증가 각인 선택" || key == "---직업 각인---" || key == "---전투 각인---" || key == "감소 각인 선택") {
                continue
            }

            if (map.contains(key)) {
                map[key] = value + map[key]!!
            } else {
                map[key] = value
            }

            homeViewModel.setUiModel(map.toSortedMap())
        }
    }

    private fun getSpinnerAdapter(spinnerArray: List<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            spinnerArray
        )
    }
}