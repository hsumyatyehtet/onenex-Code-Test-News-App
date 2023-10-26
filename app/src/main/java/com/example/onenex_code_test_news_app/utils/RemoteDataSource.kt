package com.example.onenex_code_test_news_app.utils

import retrofit2.Response

abstract class RemoteDataSource {

    protected suspend fun <T> getResponse(errorMessage : String , request : suspend () -> Response<T>) : StatefulData<T> {
        return try {
            with(request.invoke()) {
                if (isSuccessful) {
                    StatefulData.success(body())
                } else {
                    StatefulData.error(errorMessage)
                }
            }
        } catch (error : Throwable) {
            StatefulData.error("A Network Error Occurred")
        }
    }

}