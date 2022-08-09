package com.hrithik.androidassignment.repository

import com.hrithik.androidassignment.models.DTO
import com.hrithik.androidassignment.service.ServiceProvider
import io.reactivex.rxjava3.core.Observable

class FetchThemeRepository {
    private val serviceProvider = ServiceProvider.instance

    fun fetchThemesData(): Observable<DTO> {
        return serviceProvider.fetchThemeService.fetchThemes()
    }
}