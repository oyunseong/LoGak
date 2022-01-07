package com.oys.logak

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.oys.logak.databinding.ViewImprintingBinding


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

    /**
     * View Binding으로 레이아웃 파일 연결
     */
    private fun initView() {
        Log.d("++ImprintingView","initView")
        binding = ViewImprintingBinding.inflate(LayoutInflater.from(context), this, true)

    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("++ImprintingView","showDraw")
        super.onDraw(canvas)
    }

    /**
     * attrs.xml에 선언해둔 attribute를 이용하여 이를 각각의 View에 설정해준다.
     */
    private fun getAttrs(attrs: AttributeSet) {
        Log.d("++ImprintingView","getAttrs")    // 얘가 불림
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImprintingView)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        Log.d("++ImprintingView","getAttrs2")
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ImprintingView, defStyle, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        Log.d("++ImprintingView","setTypedArray")
        val bgResId = typedArray.getResourceId(R.styleable.ImprintingView_bgColor, R.color.white)
        binding.imprintingBg.setBackgroundResource(bgResId)

        val symbolResId =
            typedArray.getResourceId(R.styleable.ImprintingView_symbol, R.drawable.blank)
        binding.blank1.setImageResource(symbolResId)
        binding.blank2.setImageResource(symbolResId)
        binding.blank3.setImageResource(symbolResId)
        binding.blank4.setImageResource(symbolResId)
        binding.blank5.setImageResource(symbolResId)
        binding.blank6.setImageResource(symbolResId)
        binding.blank7.setImageResource(symbolResId)
        binding.blank8.setImageResource(symbolResId)
        binding.blank9.setImageResource(symbolResId)
        binding.blank10.setImageResource(symbolResId)
        binding.blank11.setImageResource(symbolResId)
        binding.blank12.setImageResource(symbolResId)
        binding.blank13.setImageResource(symbolResId)
        binding.blank14.setImageResource(symbolResId)
        binding.blank15.setImageResource(symbolResId)

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

    fun setText(text: String, levelText: String, overCountText: String) {
        binding.imprintingText.text = text
        binding.imprintingLevelText.text = levelText
        binding.imprintingOverCountText.text = overCountText

        Log.d("++ImprintingView", "setText: " + text)
        Log.d("++ImprintingView", "setText: " + levelText)
        Log.d("++ImprintingView", "setText: " + overCountText)
    }
}