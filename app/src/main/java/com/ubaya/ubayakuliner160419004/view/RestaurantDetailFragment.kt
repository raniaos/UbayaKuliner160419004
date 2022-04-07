package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.util.loadImage
import com.ubaya.ubayakuliner160419004.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*

class RestaurantDetailFragment : Fragment() {
    private lateinit var viewModel:RestaurantDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id: String = ""
        if (arguments != null) {
            id = RestaurantDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)
            viewModel.refreshDetail(id)

            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.resDetailLiveData.observe(viewLifecycleOwner) {
            val res = it
            res?.let {
                var id_res = it.id
                var name = it.name
                textRestaurantDetail.text = name
                imageRestaurantDetail.loadImage(it.photo, progressImageDetailRestaurant)
                if (it.likes == 1) {
                    imageLikedDetail.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                else {
                    imageLikedDetail.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }

                var address = it.address.toString()
                var desc = it.description.toString()
                var rev = it.review.toString()
                var dist = it.distance.toString()
                var phone = it.phone_number.toString()
                buttonOverview.setOnClickListener {
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToOverviewRestaurantFragment(address, rev, dist, phone, desc)
                    Navigation.findNavController(it).navigate(action)
                }
                buttonReview.setOnClickListener {
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToReviewFragment(id_res.toString(), name.toString(), rev)
                    Navigation.findNavController(it).navigate(action)
                }
                buttonMenu.setOnClickListener {
                    val action = RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMenuFragment(id_res.toString(), name.toString())
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}