package com.example.mobiletechtest.data.manager

import com.example.mobiletechtest.data.source.githubservice.GithubUserApiClient
import com.example.mobiletechtest.data.source.githubservice.mapper.mapToDomainModel
import com.example.mobiletechtest.domain.model.User
import com.example.mobiletechtest.domain.repository.GithubRepository
import com.example.mobiletechtest.data.source.githubservice.response.GithubServiceUserResponse
import io.reactivex.Observable
import io.reactivex.Single

class GithubManager: GithubRepository {

    private var client: GithubUserApiClient = GithubUserApiClient()

    override fun getUserList(query: String): Single<List<User>> {
        return client
            .getUserList(query)
            .flatMap {
                Single.just(mapToDomainModel(it))
            }
    }
}

