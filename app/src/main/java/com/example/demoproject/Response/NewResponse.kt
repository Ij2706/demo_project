package com.example.demoproject.Response

import com.example.demoproject.Response.Article

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)