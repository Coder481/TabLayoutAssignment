package com.hrithik.androidassignment.models

data class Data(
    val Cat_Name: String,
    val Category_image: Any,
    val CreatedAt: String,
    val Id: Int,
    val themes: List<Theme>,
    val themes_total_count: Int
)