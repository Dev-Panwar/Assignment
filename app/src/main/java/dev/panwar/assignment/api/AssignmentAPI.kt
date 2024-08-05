package dev.panwar.assignment.api

import dev.panwar.assignment.model.ProductApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface AssignmentAPI {
    @GET("/products")
    fun getProducts():Call<ProductApiResponse>
}