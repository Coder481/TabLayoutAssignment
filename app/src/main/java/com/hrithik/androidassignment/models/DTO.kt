package com.hrithik.androidassignment.models

data class DTO(
    val bundle_path: String = "",
    val category_path: String= "",
    val `data`: List<Data> = emptyList(),
    val error: Int = 0,
    val message: String= "",
    val newTheme: List<NewTheme> = emptyList(),
    val random: String= "",
    val sound_path: String= "",
    val status: Boolean = false,
    val thumb_big_path: String= "",
    val thumb_small_path: String= ""
)