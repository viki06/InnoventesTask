package com.example.innoventestask.network

import com.example.innoventestask.model.Model
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiController {

    @POST("dummyEndPoint")
    suspend fun savePanDetails(userDetails: Model.UserDetails): Boolean

}