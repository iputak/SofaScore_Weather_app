package hr.vsite.map.sofascore_weather_app.network.api

import hr.vsite.map.sofascore_weather_app.network.model.City
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse
import hr.vsite.map.sofascore_weather_app.network.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {

    @GET("search/")
    suspend fun getLocation(@Query("query") searchName: String): List<LocationResponse>

    @GET("{id}")
    suspend fun getCity(@Path("id") cityId: String): CityResponse

    @GET("{id}/{date}")
    suspend fun getCityDate(@Path("id") cityId: String, @Path("date") date: String): List<City>
}