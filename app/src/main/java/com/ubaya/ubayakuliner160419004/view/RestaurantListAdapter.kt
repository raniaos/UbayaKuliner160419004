package com.ubaya.ubayakuliner160419004.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.model.Restaurant
import com.ubaya.ubayakuliner160419004.util.loadImage
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class RestaurantListAdapter (val restaurantList:ArrayList<Restaurant>, var status: String) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.restaurant_list_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val res = restaurantList[position]
        with(holder.view) {
            textRestaurantHome.text = res.name
            textReviewHome.text = res.review.toString() + "/5"
            textDistanceHome.text = "${res.distance} km"
            if (status == "home") {
                buttonDetailHome.setOnClickListener {
                    val action = HomeFragmentDirections.actionItemHomeToRestaurantDetailFragment(res.id.toString())
                    Navigation.findNavController(it).navigate(action)
                }
            }
            else {
                buttonDetailHome.setOnClickListener {
                    val action = LikedRestaurantFragmentDirections.actionItemLikedToRestaurantDetailFragment(res.id.toString())
                    Navigation.findNavController(it).navigate(action)
                }
            }
            imageRestaurantHome.loadImage(res.photo, progressImageRestaurant)
        }
    }

    override fun getItemCount() = restaurantList.size

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>)
    {
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}