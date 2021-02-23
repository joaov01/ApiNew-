package br.com.newsapi.data.repository

import br.com.newsapi.data.api.API_KEY
import br.com.newsapi.data.api.ApiService
import br.com.newsapi.data.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService): IRepository{

    private val country by lazy {
        "br"
    }

    override
    suspend fun getNews(): News{
      return withContext(Dispatchers.Default){
            apiService.getTopHeadlines(country, API_KEY)
      }
    }
}
