package pl.matusiak.data.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AuthInterceptor : Interceptor {

    companion object {
        private const val AUTH_KEY = "api_key"
        private const val AUTH_VALUE = "7530c8937040eb1ebebf8dfc68091acb"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder().addQueryParameter(
            AUTH_KEY,
            AUTH_VALUE
        ).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}