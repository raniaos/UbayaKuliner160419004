package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.util.loadImage
import com.ubaya.ubayakuliner160419004.viewmodel.MenuViewModel
import com.ubaya.ubayakuliner160419004.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*

class MenuFragment : Fragment() {
    private lateinit var viewModel: MenuViewModel
    private  val menuListAdapter = MenuListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id: String = ""
        var name: String = ""
        if (arguments != null) {
            id = MenuFragmentArgs.fromBundle(requireArguments()).id
            name = MenuFragmentArgs.fromBundle(requireArguments()).restaurantName

            textRestaurantMenu.text = name

            viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
            viewModel.refreshMenu(id)

            recViewMenu.layoutManager = LinearLayoutManager(context)
            recViewMenu.adapter = menuListAdapter

            observeViewModel()

            refreshLayoutMenu.setOnRefreshListener {
                recViewMenu.visibility = View.GONE
                textErrorMenu.visibility = View.GONE
                progressLoadMenu.visibility = View.VISIBLE
                viewModel.refreshMenu(id)
                refreshLayoutMenu.isRefreshing = false
            }
        }
    }

    fun observeViewModel() {
        viewModel.menuLiveData.observe(viewLifecycleOwner) {
            menuListAdapter.updateMenuList(it)
        }

        viewModel.menuLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorMenu.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recViewMenu.visibility = View.GONE
                progressLoadMenu.visibility = View.VISIBLE
            }
            else {
                recViewMenu.visibility = View.VISIBLE
                progressLoadMenu.visibility = View.GONE
            }
        }
    }
}