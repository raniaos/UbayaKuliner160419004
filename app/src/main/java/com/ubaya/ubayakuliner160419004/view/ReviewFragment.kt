package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_home.progressLoadReservation
import kotlinx.android.synthetic.main.fragment_home.recViewReservation
import kotlinx.android.synthetic.main.fragment_home.refreshLayoutReservation
import kotlinx.android.synthetic.main.fragment_home.textErrorReservation
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {
    private lateinit var viewModel: ReviewViewModel
    private  val reviewListAdapter = ReviewListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id: String = ""
        var resto_name = ""
        var review = ""
        if (arguments != null) {
            id = ReviewFragmentArgs.fromBundle(requireArguments()).id
            resto_name = ReviewFragmentArgs.fromBundle(requireArguments()).restaurantName
            review = ReviewFragmentArgs.fromBundle(requireArguments()).review
            textRestaurantReview.text = resto_name
            textReviewReview.text = "Rating: " + review + "/5"

            viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
            viewModel.refreshReview(id)

            recViewReservation.layoutManager = LinearLayoutManager(context)
            recViewReservation.adapter = reviewListAdapter

            observeViewModel()

            refreshLayoutReservation.setOnRefreshListener {
                recViewReservation.visibility = View.GONE
                textErrorReservation.visibility = View.GONE
                progressLoadReservation.visibility = View.VISIBLE
                viewModel.refreshReview(id)
                refreshLayoutReservation.isRefreshing = false
            }
        }
    }

    fun observeViewModel() {
        viewModel.reviewLiveData.observe(viewLifecycleOwner) {
            reviewListAdapter.updateReviewList(it)
        }

        viewModel.reviewLoadErrorLiveData.observe(viewLifecycleOwner) {
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