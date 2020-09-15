package com.sample.interviewproject.api.service

import com.sample.interviewproject.model.Friend
import com.sample.interviewproject.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface SampleService {

    @GET("/profile")
    fun getProfile(): Single<Response<User>>

    @GET("/friends")
    fun getFriends(): Single<Response<List<Friend>>>
}
