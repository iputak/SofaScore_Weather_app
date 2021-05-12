package hr.vsite.map.sofascore_weather_app.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.vsite.map.sofascore_weather_app.CityActivity
import hr.vsite.map.sofascore_weather_app.adapter.CitiesRecyclerAdapter
import hr.vsite.map.sofascore_weather_app.databinding.FragmentFirstBinding
import hr.vsite.map.sofascore_weather_app.network.model.CityResponse
import hr.vsite.map.sofascore_weather_app.viewModel.CityViewModel

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CityViewModel by activityViewModels()
    private lateinit var current_city : CityResponse
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: CitiesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        recyclerView = binding.searchRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        observers()
        search()

        return binding.root
    }

    fun search(){

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = CitiesRecyclerAdapter(this.requireContext(), ArrayList<CityResponse>())
        binding.searchRecyclerView.adapter =  recyclerAdapter

        binding.search.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null) {
                    viewModel.get_location(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    fun observers(){
        viewModel.api_search.observe(viewLifecycleOwner, Observer {
            if (binding.search.query.isNotBlank() && binding.search.query.isNotEmpty() && it != null) {
                Toast.makeText(this.requireContext(), "Searching",Toast.LENGTH_LONG).show()
                viewModel.get_cities()
            }
        })

        viewModel.api_cities.observe(viewLifecycleOwner, Observer {
            if(binding.search.query.isNotBlank() && binding.search.query.isNotEmpty() && it != null){
                binding.tvHeadline.visibility = View.INVISIBLE
                recyclerAdapter.cityList = it
                recyclerAdapter.notifyDataSetChanged()
            }
        })

        viewModel.api_day.observe(viewLifecycleOwner, Observer {
            it.let {
                val intent : Intent = Intent(this.context, CityActivity::class.java)
                intent.putExtra("test", current_city)
                startActivity(intent)
            }
        })
    }
}


