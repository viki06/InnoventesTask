package com.example.innoventestask.repository

import com.example.innoventestask.model.Model
import com.example.innoventestask.network.ApiController
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiController: ApiController
) {

    suspend fun savePanDetails(
        userDetails: Model.UserDetails
    ): Boolean =
        apiController.savePanDetails(
            userDetails = userDetails
        )

}