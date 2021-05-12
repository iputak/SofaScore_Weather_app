package hr.vsite.map.sofascore_weather_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import hr.vsite.map.sofascore_weather_app.databinding.ActivityMainBinding
import hr.vsite.map.sofascore_weather_app.helpers.LanguageHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navController= findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LanguageHelper.wrapLanguage(newBase))
    }
}