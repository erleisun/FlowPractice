package com.example.flowpractice.net

import com.example.flowpractice.bean.Article
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET(value = "article")
    suspend fun getArticle(@Query("key") key: String):List<Article>


}
