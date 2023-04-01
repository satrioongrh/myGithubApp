package com.example.mygithub.api

import com.example.mygithub.data.model.DetailUserResponse
import com.example.mygithub.data.model.User
import com.example.mygithub.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q")query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username")username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username")username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username")username: String
    ): Call<ArrayList<User>>
}