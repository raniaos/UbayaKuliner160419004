package com.ubaya.ubayakuliner160419004.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.model.Review
import com.ubaya.ubayakuliner160419004.util.loadImage
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewListAdapter (val reviewList: ArrayList<Review>) : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val res = reviewList[position]
        with(holder.view) {
            textUsernameReview.text = res.users_username
            textDateReview.text = res.date
            textReviewCard.text = res.review.toString() + "/5"
            textCommentReview.text = res.comment
            imageReview.loadImage(res.photo, progressImageReview)
        }
    }

    override fun getItemCount() = reviewList.size

    fun updateReviewList(newReviewList: ArrayList<Review>)
    {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}