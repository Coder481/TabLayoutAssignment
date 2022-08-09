package com.hrithik.androidassignment.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hrithik.androidassignment.MyApp
import com.hrithik.androidassignment.R
import com.hrithik.androidassignment.models.DTO
import com.hrithik.androidassignment.repository.FetchThemeRepository
import io.reactivex.rxjava3.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException

class ThemeViewModel  : ViewModel(){

    private val repository = FetchThemeRepository()
    // Variable used to observe changes in the state of fetching themes' data
    var fetchSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    // Save error message if the fetch failed
    lateinit var errorMessage: String
    // Used to save dto after fetch is successful
    lateinit var themeDto : DTO

    fun fetchThemes() {
        repository
            .fetchThemesData()
            .subscribeWith(
                object : DisposableObserver<DTO>() {
                    override fun onNext(dto: DTO) {
                        themeDto = dto
                        fetchSuccessful.postValue(true)
                    }

                    override fun onError(throwable: Throwable) {
                        errorMessage = when (throwable) {
                            is IOException -> MyApp.getContext().getString(R.string.no_connection)
                            is HttpException -> MyApp.getContext()
                                .getString(R.string.http_exception)
                            else -> MyApp.getContext().getString(R.string.unknown_error)
                        }
                        fetchSuccessful.postValue(false)
                    }

                    override fun onComplete() {
                        Log.d("","onComplete: Fetched all data successfully")
                    }
                }
            )
    }

}