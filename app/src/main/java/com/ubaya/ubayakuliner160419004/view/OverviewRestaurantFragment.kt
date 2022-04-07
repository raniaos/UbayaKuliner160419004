package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.viewmodel.RestaurantDetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_overview_restaurant.*
import java.util.concurrent.TimeUnit

class OverviewRestaurantFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview_restaurant, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            textAddressOverview.text = "Address:\n" + OverviewRestaurantFragmentArgs.fromBundle(requireArguments()).address
            textDescriptionOverview.text = "Description:\n" + OverviewRestaurantFragmentArgs.fromBundle(requireArguments()).description
            textReviewOverview.text = "Rating: " + OverviewRestaurantFragmentArgs.fromBundle(requireArguments()).review + "/5"
            textPhoneOverview.text = OverviewRestaurantFragmentArgs.fromBundle(requireArguments()).phoneNumber
            textDistanceOverview.text = OverviewRestaurantFragmentArgs.fromBundle(requireArguments()).distance + " km from UBAYA"
        }
    }
}