package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.util.loadImage
import com.ubaya.ubayakuliner160419004.viewmodel.MenuDetailViewModel
import kotlinx.android.synthetic.main.fragment_menu_detail.*

class MenuDetailFragment : Fragment() {
    private lateinit var viewModel: MenuDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id: String = ""
        if (arguments != null) {
            id = MenuDetailFragmentArgs.fromBundle(requireArguments()).id
            viewModel = ViewModelProvider(this).get(MenuDetailViewModel::class.java)
            viewModel.refreshDetail(id)

            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.menuDetailLiveData.observe(viewLifecycleOwner) {
            val menu = it
            menu?.let {
                textMenuDetail.text = it.name
                textDescriptionMenuDetail.text = "Description: \n" + it.description
                textPriceDetail.text = "Rp" + it.price + ",-"
                imageMenuDetail.loadImage(it.photo, progressImageMenuDetail)
            }
        }
    }
}