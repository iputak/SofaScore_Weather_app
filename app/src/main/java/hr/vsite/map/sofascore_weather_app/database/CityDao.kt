package hr.vsite.map.sofascore_weather_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse

@Dao
interface CityDao {

    @Query("SELECT * FROM cityresponse WHERE recent = 1")
    suspend fun getAllCities(): List<CityResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityResponse)

    @Query("SELECT * FROM cityresponse WHERE favorite = 1")
    suspend fun getFavorites(): List<CityResponse>

}