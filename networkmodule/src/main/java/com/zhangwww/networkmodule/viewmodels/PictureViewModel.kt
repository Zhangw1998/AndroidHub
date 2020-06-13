package com.zhangwww.networkmodule.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhangwww.networkmodule.model.PictureModel
import com.zhangwww.networkmodule.repositories.PictureRepository
import kotlinx.coroutines.launch

class PictureViewModel: ViewModel() {

    private val repository by lazy { PictureRepository() }

    val pictureList = MutableLiveData<List<PictureModel>>()

    fun updatePictureList(page: Int, count: Int) {
        viewModelScope.launch {
            pictureList.value = repository.fetchGirlPictures(page, count)
        }
    }

}