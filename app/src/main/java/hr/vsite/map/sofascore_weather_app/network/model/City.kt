package hr.vsite.map.sofascore_weather_app.network.model

import java.io.Serializable

data class City(
    val id: Long,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String,
    val applicable_date: String,
    val min_temp: Int,
    val max_temp: Int,
    val the_temp: Int,
    val wind_speed: Int,
    val wind_direction: Int,
    val air_pressure: Double,
    val humidity: Double,
    val visibility: Int,
    val predictability: Int
): Serializable
