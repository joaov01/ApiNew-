package br.com.newsapi.data.api

import br.com.newsapi.data.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String,
    @Query("apiKey") apiKey: String): News
}