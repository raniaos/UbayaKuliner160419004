package com.ubaya.ubayakuliner160419004.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ubayakuliner160419004.GlobalData
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.util.loadImage
import com.ubaya.ubayakuliner160419004.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_profile.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.activity?.bottomNav?.visibility = View.GONE
        (activity as MainActivity).supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        GlobalData.username = ""
        buttonLogin.setOnClickListener {
            GlobalData.username = editUsername.text.toString()
            val action = LoginFragmentDirections.actionLoginFragmentToItemHome()
            Navigation.findNavController(view).navigate(action)
        }

        buttonRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}