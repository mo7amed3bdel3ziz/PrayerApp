package com.example.prayerapplication.network


import com.example.prayerapplication.models.Data
import com.example.prayerapplication.models.ExampleJson2KtKotlin
import com.example.prayerapplication.models.QiblaModel
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface ApiService {


  @Headers("Content-Type: application/json")
    @GET("v1/calendar/2017/4")
    fun GetDetailsAPI(
        @Query("latitude")  latitude: Double,
        @Query("longitude") longitude: Double,
       // @Query("longitude") longitude2: Double,
        @Query("method")     method: String
        ): Deferred<ExampleJson2KtKotlin<Data>>



  @Headers("Content-Type: application/json")
    @GET("v1/qibla/25.4106386/51.1846025")
    fun GetQiblaAPI(): Deferred<ExampleJson2KtKotlin<QiblaModel>>




}