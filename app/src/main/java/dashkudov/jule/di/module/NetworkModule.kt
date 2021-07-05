package dashkudov.jule.di.module

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dashkudov.jule.BuildConfig
import dashkudov.jule.Config
import dashkudov.jule.dataSources.ApiDataSource
import dashkudov.jule.repository.ApiRepository
import dashkudov.jule.repository.ApiRepositoryImpl
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession
import kotlin.io.path.ExperimentalPathApi


@Module
class NetworkModule {

    companion object {
        private const val DEFAULT_TIMEOUT: Long = 5
        private const val DEFAULT_CACHE_AGE: Int = 2
        private const val LOG_TAG: String = "LOGGING_INTERCEPTOR"
    }
    object AuthTokenInterceptor : Interceptor {
        var token: String? = null
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val builder = request.newBuilder()
            builder.addHeader("Authorization", "Bearer $token")
            return chain.proceed(builder.build())
        }
    }

    @Provides
    @Singleton
    fun provideApiHeadersInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.addHeader("Accept", "application/json")
            request.addHeader("Client", "android")
            request.addHeader("Connection", "close")
            request.addHeader("Build", "${BuildConfig.VERSION_CODE}")
            request.addHeader("Accept-language", Locale.getDefault().language)
            request.addHeader("Cache-Control", "max-age=$DEFAULT_CACHE_AGE")
            chain.proceed(request.build())
        }

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(URL(Config.API_HOST))
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    class OkHttpLogoInterceptor : Interceptor {
        @ExperimentalPathApi
        @RequiresApi(Build.VERSION_CODES.O)
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                // TODO: 2/14/21 Remove after stabilization app
                println(
                        "okhttp " + String.format(
                                "Sending request %s on %s%n%s", request.url,
                                chain.connection(), request.headers
                        )
                )
                val t1 = System.nanoTime()
                val response: Response = chain.proceed(chain.request())
                val t2 = System.nanoTime()
                println(
                        "okhttp " + String.format(
                                Locale.getDefault(), "Received response from %s in %.1f ms %n%s",
                                response.request.url, (t2 - t1) / 1e6, response.headers
                        )
                )
                val mediaType: MediaType? = response.body?.contentType()
                val content: String? = response.body?.string()
                if (content != null) {
                    println("okhttp $content")
                }
                return response.newBuilder()
                        .body((content ?: "").toResponseBody(mediaType))
                        .build()
        }
    }

    @Provides
    fun provideHttpLogoInterceptor() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun providesOkHttpClient(
        apiHeadersInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor?,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthTokenInterceptor)
        .addInterceptor(apiHeadersInterceptor)
        .addInterceptor(OkHttpLogoInterceptor())
        .apply {
            if (loggingInterceptor != null) {
                addInterceptor(loggingInterceptor)
            } else return@apply
        }
        .cache(null)
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun providesGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiDataSource =
        retrofit.create(ApiDataSource::class.java)

    @Provides
    @Singleton
    fun provideApiRepository(apIs: ApiDataSource): ApiRepository = ApiRepositoryImpl(apIs)

}