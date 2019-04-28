package com.example.mobiletechtest.domain.repository

import com.example.mobiletechtest.domain.model.User
import io.reactivex.Single

interface GithubRepository {

    fun getUserList(query: String): Single<List<User>>

}