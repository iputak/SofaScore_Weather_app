package hr.vsite.map.sofascore_weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import hr.vsite.map.sofascore_weather_app.databinding.ActivityAboutBinding
import hr.vsite.map.sofascore_weather_app.viewModel.CityViewModel

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    private val viewModel: CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //reference to ImageView
        val backClick = binding.backButton as ImageView
        backClick.setOnClickListener {
            finish()
        }
    }
}