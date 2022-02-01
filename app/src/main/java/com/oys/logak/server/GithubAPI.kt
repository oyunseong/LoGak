package com.oys.logak.server

import com.oys.logak.model.Imprint
import retrofit2.Call
import retrofit2.http.GET

interface GithubAPI {
    @GET("Desktop/imprintList.json")
    fun getImprintingArray(): Call<Imprint>
}