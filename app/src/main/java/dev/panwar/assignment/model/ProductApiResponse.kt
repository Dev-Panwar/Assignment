package dev.panwar.assignment.model

data class ProductApiResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)