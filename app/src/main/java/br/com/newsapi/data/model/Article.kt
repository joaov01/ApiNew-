package br.com.newsapi.data.model

import java.io.Serializable

class Article (val author: String,
                                      val title: String,
                                      val description:String,
                                      val url: String,
                                      val urlToImage: String,
                                      val publishedAt: String,
                                      val content:String, private val source: Source): Serializable
