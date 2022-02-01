package com.oys.logak

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.oys.logak.databinding.ViewImprintingBinding
import com.oys.logak.ui.home.ImprintingModel

/**
 * 각인 선택시 앱 상단에 출력되는 커스텀 뷰입니다.
 * 출력 내용
 * 1. 각인 이름
 * 2. 각인 스코어 상태에 맞는 보석 출력
 * 3. 각인 초과 레벨
 * */
class ImprintingView : ConstraintLayout {

    // 코드에서 View 객체를 생성할 때 호출하는 생성자
    constructor(context: Context) : super(context) {
        Log.d("++ImprintingView", "1번 생성자")
        initView()
    }

    // 얘가 호출됨!
    // 레이아웃 xml에 등록한 View가 안드로이드에 의해 inflate될 때 호출되는 생성자
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        Log.d("++ImprintingView", "2번 생성자")
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs) {
        Log.d("++ImprintingView", "3번 생성자")
        initView()
        getAttrs(attrs, defStyle)
    }

    // 뷰 참조할 때 사용
    private lateinit var binding: ViewImprintingBinding

    private val blankArray by lazy {
        arrayOf(
            binding.blank1,
            binding.blank2,
            binding.blank3,
            binding.blank4,
            binding.blank5,
            binding.blank6,
            binding.blank7,
            binding.blank8,
            binding.blank9,
            binding.blank10,
            binding.blank11,
            binding.blank12,
            binding.blank13,
            binding.blank14,
            binding.blank15,
        )
    }

    /**
     * View Binding으로 레이아웃 파일 연결
     */
    private fun initView() {
        Log.d("++ImprintingView", "initView")
        binding = ViewImprintingBinding.inflate(LayoutInflater.from(context), this, true)

    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("++ImprintingView", "showDraw")
        super.onDraw(canvas)
    }

    /**
     * attrs.xml에 선언해둔 attribute를 이용하여 이를 각각의 View에 설정해준다.
     */
    private fun getAttrs(attrs: AttributeSet) {
        Log.d("++ImprintingView", "getAttrs")    // 얘가 불림
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImprintingView)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        Log.d("++ImprintingView", "getAttrs2")
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImprintingView, defStyle, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        Log.d("++ImprintingView", "setTypedArray")
        val bgResId =
            typedArray.getResourceId(R.styleable.ImprintingView_bgColor, R.color.background_color)
        binding.imprintingBg.setBackgroundResource(bgResId)

        val symbolResId =
            typedArray.getResourceId(R.styleable.ImprintingView_symbol, R.drawable.blank)

        blankArray.forEachIndexed{i,v ->
            v.setImageResource(symbolResId)
        }


        val textColor = typedArray.getColor(R.styleable.ImprintingView_textColor, 0)
        binding.imprintingText.setTextColor(textColor)
        binding.imprintingLevelText.setTextColor(textColor)
        binding.imprintingOverCountText.setTextColor(textColor)

        val text = typedArray.getString(R.styleable.ImprintingView_text)
        val levelText = typedArray.getString(R.styleable.ImprintingView_levelText)
        val overCountText = typedArray.getString(R.styleable.ImprintingView_overCountText)
        binding.imprintingText.text = text
        binding.imprintingLevelText.text = levelText
        binding.imprintingOverCountText.text = overCountText

        Log.d("++ImprintingView", "" + text)
        Log.d("++ImprintingView", "" + levelText)
        Log.d("++ImprintingView", "" + overCountText)

        typedArray.recycle()
    }

    /**
     *
     *
     * */
    @SuppressLint("UseCompatLoadingForDrawables")
    fun setImprintingModel(imprintingModel: ImprintingModel) {
        var textColor = ContextCompat.getColor(context, R.color.red)
        var resId: Drawable?
        var blankId = resources.getDrawable(R.drawable.blank)
        binding.imprintingOverCountText.setTextColor(textColor)

        if (imprintingModel.text == "공격력 감소" || imprintingModel.text == "공격속도 감소" || imprintingModel.text == "방어력 감소" || imprintingModel.text == "이동속도 감소") {
            textColor = ContextCompat.getColor(context, R.color.red)
            binding.imprintingText.setTextColor(textColor)
            binding.imprintingLevelText.setTextColor(textColor)
            binding.imprintingText.text = imprintingModel.text
            binding.imprintingLevelText.text = imprintingModel.getLevel(imprintingModel.score)
            binding.imprintingOverCountText.text =
                imprintingModel.overScore(imprintingModel.score)
            resId = resources.getDrawable(R.drawable.minus)
        } else {
            textColor = ContextCompat.getColor(context, R.color.white)
            binding.imprintingText.setTextColor(textColor)
            binding.imprintingLevelText.setTextColor(textColor)
            binding.imprintingText.text = imprintingModel.text
            binding.imprintingLevelText.text = imprintingModel.getLevel(imprintingModel.score)
            binding.imprintingOverCountText.text =
                imprintingModel.overScore(imprintingModel.score)
            resId = resources.getDrawable(R.drawable.plus)
        }

        blankArray.forEachIndexed { i, view ->
            if (imprintingModel.score > i){
                view.setImageDrawable(resId)
            }else{
                view.setImageDrawable(blankId)
            }
        }
    }
}
