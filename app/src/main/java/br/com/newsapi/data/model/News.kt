package br.com.newsapi.data.model

import java.io.Serializable


class News (private var status: String = "",
           private var totalResults: Int = 0,
           var articles: List<Article> = mutableListOf()): Serializable
