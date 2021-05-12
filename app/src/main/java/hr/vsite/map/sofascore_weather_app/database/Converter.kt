package hr.vsite.map.sofascore_weather_app.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse

class Converter {
    @TypeConverter
    fun listToJson(value:List<CityResponse>?) = Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<CityResponse>::class.java).toList()
}