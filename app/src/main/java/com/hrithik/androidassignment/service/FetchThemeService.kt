package com.hrithik.androidassignment.service

import com.hrithik.androidassignment.models.DTO
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface FetchThemeService {

    @GET("getallthemes?page=0&Application_Id=83&languages=256")
    fun fetchThemes() : Observable<DTO>

}