package com.example.jetpacksample.feature.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacksample.model.News
import com.example.jetpacksample.repository.NewsRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel : ViewModel(), KoinComponent {
    private val newsRepository: NewsRepository by inject()
    private val newsMutableLiveData = MutableLiveData<News>()
    val newsLiveData: LiveData<News> = newsMutableLiveData

    fun getNews(source: String) = viewModelScope.launch {
        newsRepository.getNews(source).let {
            newsMutableLiveData.postValue(it)
        }
    }
}