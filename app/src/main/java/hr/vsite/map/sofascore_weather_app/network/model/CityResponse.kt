package hr.vsite.map.sofascore_weather_app.network.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hr.vsite.map.sofascore_weather_app.database.Converter
import java.io.Serializable
import java.time.DateTimeException


@Entity(tableName = "cities")
data class CityResponse(
    @PrimaryKey
    val woeid: Int,
    @TypeConverters(Converter::class)
    val consolidated_weather: List<City>,
    val timezone_name: String,
    val title: String,
    val time: DateTimeException,
    val latt_long: String,
    @Transient
    @ColumnInfo(name = "favorite")
    var favorite : Boolean = false,
    @Transient
    @ColumnInfo(name = "recent")
    val recent : Boolean = false

) : Serializable


