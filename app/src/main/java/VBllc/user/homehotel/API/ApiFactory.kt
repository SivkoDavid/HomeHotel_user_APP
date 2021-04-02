package VBllc.user.homehotel.API

import VBllc.user.homehotel.URL_REST
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiFactory {
    val API_BASE_URL  = URL_REST

    fun createAPIwithCoroutines():API{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL) // Базовый URL
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient()) // Конвертер JSON
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(API::class.java )
    }

}