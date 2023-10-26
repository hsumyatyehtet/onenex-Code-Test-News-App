package com.example.onenex_code_test_news_app.utils

const val BASE_URL = "https://newsapi.org"

//api
const val GET_NEW_LIST = "/v2/top-headlines"

//hsumyatyehtet
//const val API_KEY_DATA = "5a5f48188a5248c38370532e26eb4c55"
const val API_KEY_DATA="6197ff3b80e04ee386a9f5eff9787002"

//Data
const val QUERY_DATA_SOURCE = "bbc-news"

//PARAM
const val PARAM_API_KEY = "apiKey"
const val PARAM_SORUCES = "sources"
const val PARAM_CATEGORY = "category"

class ApiConstants {
    companion object{
        const val HEADER_ACCEPT = "Accept"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_VALUE = "application/json"
    }
}

