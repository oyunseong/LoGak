package com.oys.logak.ui

data class ImprintingModel(
    val text: String,
    var score: Int
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

    override fun equals(other: Any?): Boolean {
        return this.text == (other as? ImprintingModel)?.text
    }

    override fun hashCode(): Int {
        return  ((text.hashCode() ?: 0) * 31) * 31
    }
}