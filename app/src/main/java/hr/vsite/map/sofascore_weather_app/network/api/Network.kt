package hr.vsite.map.sofascore_weather_app.network.api

import hr.vsite.map.sofascore_weather_app.network.model.City
import hr.vsite.map.sofascore_weather_app.network.model.LocationResponse
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    private val service: MetaWeatherService
    private val baseUrl = "https://www.metaweather.com/api/location/"

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(MetaWeatherService::class.java)
    }

    suspend fun getLocation(searchName: String): List<LocationResponse> {
        return service.getLocation(searchName)
    }

    suspend fun getCity(cityId: Int): CityResponse {
        return service.getCity(cityId.toString())
    }

    suspend fun getCityDate(cityId: String, date: String): List<City> {
        return service.getCityDate(cityId, date.replace("-", "/"))
    }

}