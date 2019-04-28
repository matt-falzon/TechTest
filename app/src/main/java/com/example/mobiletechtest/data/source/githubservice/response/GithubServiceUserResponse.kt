package com.example.mobiletechtest.data.source.githubservice.response

import com.google.gson.annotations.SerializedName

class GithubServiceUserResponse {

    @SerializedName("items") lateinit var users: List<Items>
    @SerializedName("total_count") val totalCount : Int = -1
    @SerializedName("incomplete_results") var incompleteResults : Boolean? = null

    class Items {
        @SerializedName("login")lateinit var login: String
        @SerializedName("id") var id: Int = -1
        @SerializedName("node_id")lateinit var nodeId: String
        @SerializedName("avatar_url")lateinit var avatarUrl: String
        @SerializedName("gravatar_id")lateinit var gravatarId: String
        @SerializedName("url")lateinit var url: String
        @SerializedName("followers_url")lateinit var followersUrl: String
        @SerializedName("subscriptions_url")lateinit var subscruptionsUrl: String
        @SerializedName("organizations_url")lateinit var organizationsUrl: String
        @SerializedName("repos_url")lateinit var reposUrl: String
        @SerializedName("recieved_events_url")lateinit var recievedEventsUrl: String
        @SerializedName("type")lateinit var type: String
        @SerializedName("score") var score: Double = -1.0
    }
}