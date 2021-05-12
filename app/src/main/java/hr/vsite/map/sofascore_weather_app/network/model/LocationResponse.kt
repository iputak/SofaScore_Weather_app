package hr.vsite.map.sofascore_weather_app.network.model

import java.io.Serializable

data class LocationResponse(
    val woeid: Int,
    val title: String,
    val lat_long: String,
    val location_type: String
) : Serializable