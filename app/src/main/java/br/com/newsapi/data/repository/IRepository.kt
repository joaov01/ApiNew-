package br.com.newsapi.data.repository

import br.com.newsapi.data.model.News

interface IRepository {
    suspend fun getNews(): News
}