package com.example.mobiletechtest.data.source.githubservice

import com.example.mobiletechtest.data.source.githubservice.response.GithubServiceUserResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface GithubServiceApi {

    @GET("/search/users")
    @Headers("Accept: application/json")
    fun getUserList(@Header("Authorization") token: String, @Query("q")query: String): Single<GithubServiceUserResponse>

}