package hr.vsite.map.sofascore_weather_app.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.vsite.map.sofascore_weather_app.network.api.Network
import hr.vsite.map.sofascore_weather_app.network.model.City
import hr.vsite.map.sofascore_weather_app.network.model.LocationResponse
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse
import kotlinx.coroutines.launch

class CityViewModel(): ViewModel() {

    val api_search = MutableLiveData<List<LocationResponse>>()
    val api_cities = MutableLiveData<ArrayList<CityResponse>>()
    val api_day = MutableLiveData<List<City>>()

    fun get_location(search_name : String){
        viewModelScope.launch {
            api_search.value =  Network().getLocation(search_name)
        }
    }

    fun get_cities(){
        api_cities.value = ArrayList()
        viewModelScope.launch {
            val list = ArrayList<CityResponse>()
            api_search.value?.forEach {
                list.add(Network().getCity(it.woeid))
            }
            api_cities.value = list
        }
    }

    fun get_city_date(city_id: String, date : String){
        viewModelScope.launch {
            api_day.value = Network().getCityDate(city_id, date)
        }
    }


}