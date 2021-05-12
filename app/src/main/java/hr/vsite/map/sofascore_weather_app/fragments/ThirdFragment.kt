package hr.vsite.map.sofascore_weather_app.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Process
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import hr.vsite.map.sofascore_weather_app.AboutActivity
import hr.vsite.map.sofascore_weather_app.MainActivity
import hr.vsite.map.sofascore_weather_app.R
import hr.vsite.map.sofascore_weather_app.databinding.FragmentThirdBinding
import hr.vsite.map.sofascore_weather_app.helpers.LanguageHelper
import kotlinx.android.synthetic.main.popover_clear_my_cities_list.view.*


const val LANGUAGE_EN = "en"
const val LANGUAGE_HR = "hr"

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!
    private var firstSelection = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        val root = binding.root

        //Change app language - spinner
        val languageList = resources.getStringArray(R.array.languages_array)
        binding.spLanguage.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, languageList)

        val currentLanguageCode = LanguageHelper.getPreferredLanguage(requireContext())
        if (currentLanguageCode == LANGUAGE_EN) {
            binding.spLanguage.setSelection(0)
        }
        binding.spLanguage.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (firstSelection) {
                        firstSelection = false
                    } else {
                        val item = parent.getItemAtPosition(position) as String
                        val tempLanguageCode = languageStringToCode(item)
                        if (tempLanguageCode != currentLanguageCode) {
                            LanguageHelper.setPreferredLanguage(requireContext(), tempLanguageCode)
                            restartApp()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // nothing happens
                }
            }

        //Change city - spinner
        val cityList = resources.getStringArray(R.array.cities_array)
        binding.spCity.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cityList)

        //More info activity
        val moreInfo = binding.btnMoreInfo as Button
        moreInfo.setOnClickListener {
            val intent = Intent(activity, AboutActivity::class.java)
            startActivity(intent)
        }

        //Clear my cities list
        val clearCities = binding.btnClearCities as Button
        clearCities.setOnClickListener {
            val mDialogView =
                LayoutInflater.from(context).inflate(R.layout.popover_clear_my_cities_list, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            //Show dialog
            val mAlertDialog = mBuilder.show()

            //Buttons on popover window
            //Clear

            mDialogView.btn_clear.setOnClickListener {
                //Remove popover
                mAlertDialog.dismiss()
                //Show snackbar
                showCitiesSnackbar()
            }

            //Cancel
            mDialogView.btn_cancel.setOnClickListener {
                //Cancel-remove popover
                mAlertDialog.dismiss()
            }

        }

        //Clear recent search list
        val clearSearch = binding.btnClearSearchList as Button
        clearSearch.setOnClickListener {
            //show popup window
            val mDialogView = LayoutInflater.from(context)
                .inflate(R.layout.popover_clear_recent_search_list, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)

            //Show dialog
            val mAlertDialog = mBuilder.show()


            //Buttons on popover window
            //Clear
            mDialogView.btn_clear.setOnClickListener {
                //Remove popover
                mAlertDialog.dismiss()
                //Show snackbar
                showSearchSnackbar()
            }

            //Cancel
            mDialogView.btn_cancel.setOnClickListener {
                //Cancel-remove popover
                mAlertDialog.dismiss()
            }
        }

        return binding.root
    }

    private fun showSearchSnackbar() {
        view?.let {
            Snackbar.make(it, R.string.clearSearchSnackbarText, Snackbar.LENGTH_INDEFINITE)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("#1c1f22"))
                .setActionTextColor(Color.parseColor("#ffffff"))
                .setAction("X") { println("Snackbar Set Action - OnClick.") }
                .show()
        }
    }

    private fun showCitiesSnackbar() {
        view?.let {
            Snackbar.make(it, R.string.clearCitiesSnackbarText, Snackbar.LENGTH_INDEFINITE)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("#1c1f22"))
                .setActionTextColor(Color.parseColor("#ffffff"))
                .setAction("X") { println("Snackbar Set Action - OnClick.") }
                .show()
        }
    }

    private fun languageStringToCode(languageString: String): String {
        return when (languageString) {
            requireContext().getString(R.string.english) -> LANGUAGE_EN
            requireContext().getString(R.string.croatian) -> LANGUAGE_HR
            else -> ""
        }
    }

    private fun restartApp() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(activity, MainActivity::class.java)
            val cn = intent.component
            val mainIntent = Intent.makeRestartActivityTask(cn)
            startActivity(mainIntent)
            Process.killProcess(Process.myPid())
        }, 300)
    }
}