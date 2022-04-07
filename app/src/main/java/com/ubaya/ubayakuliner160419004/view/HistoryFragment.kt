package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.viewmodel.ReservationViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HistoryFragment : Fragment() {
    private lateinit var viewModel: ReservationViewModel
    private val reservationListAdapter = ReservationListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ReservationViewModel::class.java)
        viewModel.refreshFinished()

        recViewReservation.layoutManager = LinearLayoutManager(context)
        recViewReservation.adapter = reservationListAdapter

        observeViewModel()

        refreshLayoutReservation.setOnRefreshListener {
            recViewReservation.visibility = View.GONE
            textErrorReservation.visibility = View.GONE
            progressLoadReservation.visibility = View.VISIBLE
            viewModel.refreshFinished()
            refreshLayoutReservation.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.reservationLiveData.observe(viewLifecycleOwner) {
            reservationListAdapter.updateReservationList(it)
        }

        viewModel.reservationLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorReservation.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recViewReservation.visibility = View.GONE
                progressLoadReservation.visibility = View.VISIBLE
            } else {
                recViewReservation.visibility = View.VISIBLE
                progressLoadReservation.visibility = View.GONE
            }

        }
    }
}