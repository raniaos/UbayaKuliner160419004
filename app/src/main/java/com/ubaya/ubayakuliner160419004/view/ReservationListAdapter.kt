package com.ubaya.ubayakuliner160419004.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.model.Reservation
import com.ubaya.ubayakuliner160419004.util.loadImage
import kotlinx.android.synthetic.main.reservation_list_item.view.*

class ReservationListAdapter (val reservationList:ArrayList<Reservation>) : RecyclerView.Adapter<ReservationListAdapter.ReservationViewHolder>() {
    class ReservationViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.reservation_list_item, parent, false)
        return ReservationListAdapter.ReservationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val res = reservationList[position]
        with(holder.view) {
            textRestaurantReservation.text = res.name
            textTimeReservation.text = "Date: " + res.time
            textNotesReservation.text = "Notes: " + res.notes
            imageReservation.loadImage(res.photo, progressImageReservation)
        }
    }

    override fun getItemCount() = reservationList.size

    fun updateReservationList(newReservationList: ArrayList<Reservation>)
    {
        reservationList.clear()
        reservationList.addAll(newReservationList)
        notifyDataSetChanged()
    }
}