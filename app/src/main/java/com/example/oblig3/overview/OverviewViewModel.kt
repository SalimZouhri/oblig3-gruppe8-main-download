package com.example.oblig3.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oblig3.network.PhotoImage
import com.example.oblig3.network.PictureApi
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */

enum class PictureApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<PictureApiStatus>()

    private val _photos = MutableLiveData<List<PhotoImage>>()

    // The external immutable LiveData for the request status
    val status: LiveData<PictureApiStatus> = _status

    val photos: LiveData<List<PhotoImage>> = _photos

    /**
     * Call getPhotoImages() on init so we can display status immediately.
     */
    init {
        getPhotoImages()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getPhotoImages() {
        viewModelScope.launch {
            _status.value = PictureApiStatus.LOADING
            try {
                _photos.value = PictureApi.retrofitService.getPhotos()
                _status.value = PictureApiStatus.DONE

            } catch (e: Exception) {
                _status.value = PictureApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}