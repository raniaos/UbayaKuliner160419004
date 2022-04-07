package com.ubaya.ubayakuliner160419004.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419004.GlobalData
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.viewmodel.RestaurantViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var viewModel:RestaurantViewModel
    private  val restaurantListAdapter = RestaurantListAdapter(arrayListOf(), "home")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.activity?.bottomNav?.visibility = View.VISIBLE
        (activity as MainActivity).supportActionBar?.show()
        var username = ""
        var shared = this.activity?.getSharedPreferences(this.activity?.packageName, Context.MODE_PRIVATE)
        username = shared?.getString("USERNAME", "").toString()
        Log.d("ausername", username)
//        username = "rania"

        if (GlobalData.username != "") {
            viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
            viewModel.refreshHome()

            recViewReservation.layoutManager = LinearLayoutManager(context)
            recViewReservation.adapter = restaurantListAdapter

            observeViewModel()

            textWelcome.text = "Welcome back, " + GlobalData.username + "!"

            refreshLayoutReservation.setOnRefreshListener {
                recViewReservation.visibility = View.GONE
                textErrorReservation.visibility = View.GONE
                progressLoadReservation.visibility = View.VISIBLE
                viewModel.refreshHome()
                refreshLayoutReservation.isRefreshing = false
            }
        }
        else {
            val action = HomeFragmentDirections.actionItemHomeToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            restaurantListAdapter.updateRestaurantList(it)
        }

        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorReservation.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recViewReservation.visibility = View.GONE
                progressLoadReservation.visibility = View.VISIBLE
            }
            else {
                recViewReservation.visibility = View.VISIBLE
                progressLoadReservation.visibility = View.GONE
            }
        }
    }
}