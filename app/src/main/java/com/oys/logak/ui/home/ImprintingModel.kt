package com.oys.logak.ui.home

import androidx.annotation.DrawableRes


/**
 * 1. 각인 text / text 가 map 에 올라갔을 때 view visible
 * 2. 각인 Level
 * 3. 초과 score
 * 4. score 만큼 보석 박기
 * */

data class ImprintingModel(
    val text: String,
    val score: Int,
//    val test:String
) {
    fun getLevel(score: Int): String {
        return when {
            score in 5..9 -> "Lv. 1"
            score in 10..14 -> "Lv. 2"
            score >= 15 -> "Lv. 3"
            else -> ""
        }
    }

    fun overScore(score: Int): String {
        if (score <= 15) return ""
        return "+${score - 15}"
    }
//
//    override fun equals(other: Any?): Boolean {
//        return this.text == (other as? ImprintingModel)?.text
//    }
//
//    override fun hashCode(): Int {
//        return  ((text.hashCode() ?: 0) * 31) * 31
//    }
}

