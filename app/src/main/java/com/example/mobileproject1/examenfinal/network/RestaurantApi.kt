package com.example.mobileproject1.examenfinal.network




import com.example.mobileproject1.examenfinal.model.Restaurant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestaurantsApiService {
    @GET("15e41a39c9c251cad31639feabf0ce4ba131bb19/restaurants.json")
    suspend fun getRestaurants(): List<Restaurant>

    companion object {
        fun create(): RestaurantsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/jorgegit/f1c89ab2e409c98ec618fdb9e75077bd/raw/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RestaurantsApiService::class.java)
        }
    }
}
