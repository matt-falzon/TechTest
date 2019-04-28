package com.example.mobiletechtest.data.source.githubservice

import com.example.mobiletechtest.data.source.githubservice.mapper.mapToDomainException
import com.example.mobiletechtest.data.source.githubservice.response.GithubServiceUserResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Base64.NO_WRAP



class GithubUserApiClient {
    private val GITHUB_BASE_URL= "https://api.github.com"

    private var service: GithubServiceApi
    private var token = "Basic bWF0dC1mYWx6b246NjQwZWY1NWYxNjk3Y2E0MzU4N2ViOWFjN2YwZDczNmZmYjQ2NzI0MA=="

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        service = retrofit.create(GithubServiceApi::class.java)
    }

    fun getUserList(query: String): Single<GithubServiceUserResponse> {
        return service.getUserList(token, query)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext { throwable ->
                Single.error(mapToDomainException(throwable))
            }
    }
}