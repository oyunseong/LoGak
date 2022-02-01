package com.oys.logak.model

import com.google.gson.annotations.SerializedName

data class Imprint(
    @SerializedName("jobImprintList")
    val jobImprintList: List<String>,

    @SerializedName("battleImprintList")
    val battleImprintList: List<String>,

    @SerializedName("decreaseImprintList")
    val decreaseImprintList: List<String>,

    @SerializedName("accScoreList")
    val accScoreList: List<String>,

    @SerializedName("abilityScoreList")
    val abilityScoreList: List<String>,

    @SerializedName("bookScoreList")
    val bookScoreList: List<String>,
) {

    fun getJobAndBattleImprintList(): List<String> {
        return jobImprintList + battleImprintList
    }

}
