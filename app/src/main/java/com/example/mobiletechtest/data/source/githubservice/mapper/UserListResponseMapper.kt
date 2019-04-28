package com.example.mobiletechtest.data.source.githubservice.mapper

import com.example.mobiletechtest.data.source.githubservice.response.GithubServiceUserResponse
import com.example.mobiletechtest.domain.model.User

fun mapToDomainModel(userResponse: GithubServiceUserResponse): List<User> {
            val userList = mutableListOf<User>()

        userResponse.users.forEach{
            userList.add(User(it.login, it.type, it.avatarUrl, it.score))
        }

        return userList
    }

