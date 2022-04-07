package com.ubaya.ubayakuliner160419004.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.viewmodel.RestaurantViewModel
import kotlinx.android.synthetic.main.fragment_liked_restaurant.*

class LikedRestaurantFragment : Fragment() {
    private lateinit var viewModel: RestaurantViewModel
    private  val restaurantListAdapter = RestaurantListAdapter(arrayListOf(), "liked")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liked_restaurant, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        viewModel.refreshLikedRestaurant()

        recViewLikedRestaurant.layoutManager = LinearLayoutManager(context)
        recViewLikedRestaurant.adapter = restaurantListAdapter

        observeViewModel()

        refreshLayoutLikedRestaurant.setOnRefreshListener {
            recViewLikedRestaurant.visibility = View.GONE
            textErrorLikedRestaurant.visibility = View.GONE
            progressLoadLikedRestaurant.visibility = View.VISIBLE
            viewModel.refreshLikedRestaurant()
            refreshLayoutLikedRestaurant.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            restaurantListAdapter.updateRestaurantList(it)
        }

        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorLikedRestaurant.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recViewLikedRestaurant.visibility = View.GONE
                progressLoadLikedRestaurant.visibility = View.VISIBLE
            }
            else {
                recViewLikedRestaurant.visibility = View.VISIBLE
                progressLoadLikedRestaurant.visibility = View.GONE
            }
        }
    }
}