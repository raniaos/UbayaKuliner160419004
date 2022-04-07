package com.ubaya.ubayakuliner160419004.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ubayakuliner160419004.GlobalData
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.util.loadImage
import com.ubaya.ubayakuliner160419004.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var id: String = ""
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.refresh(GlobalData.username)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            val user = it
            user?.let {
                editUsernameProfile.setText(it.username)
                editPasswordProfile.setText(it.password)
                editRepPasswordProfile.setText(it.password)
                editNameProfile.setText(it.name)
                imageProfile.loadImage(it.photo, progressImageProfile)
            }
        }
    }
}