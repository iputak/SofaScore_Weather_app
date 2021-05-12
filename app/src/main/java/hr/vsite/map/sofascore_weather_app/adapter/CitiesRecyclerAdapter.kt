package hr.vsite.map.sofascore_weather_app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import hr.vsite.map.sofascore_weather_app.R
import hr.vsite.map.sofascore_weather_app.databinding.SearchCityItemViewBinding
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse

class CitiesRecyclerAdapter(val context: Context, var cityList: ArrayList<CityResponse>) :
    RecyclerView.Adapter<CitiesRecyclerAdapter.CitiesViewHolder>() {

    class CitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SearchCityItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view =
            LayoutInflater.from(this.context).inflate(R.layout.search_city_item_view, parent, false)
        return CitiesViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        //Title
        holder.binding.tvAbout.text = cityList[position].title

        //Location
        val loc = cityList[position].latt_long.split(".", ",") // split the text by spaces
        val location = loc[0] + "°" + loc[1].take(2) + "′N, " + loc[2] + "°" + loc[3].take(2) + "′W"
        holder.binding.tvLocation.text = location
        //Distance - timezone
        holder.binding.tvDistance.text = cityList[position].timezone_name
        //Temperature
        holder.binding.tvTemp.text = """${
            cityList[position].consolidated_weather.get(0).the_temp.toInt().toString()
        }°"""

        //Weather icon
        val src =
            "@drawable/ic_" + cityList[position].consolidated_weather.get(0).weather_state_abbr
        val img_res = context.resources.getIdentifier(src, null, context.packageName)
        val icon = context.resources.getDrawable(img_res)
        holder.binding.tempIcon.load(icon)

    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}