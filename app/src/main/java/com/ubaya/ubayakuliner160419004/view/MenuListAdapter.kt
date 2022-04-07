package com.ubaya.ubayakuliner160419004.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ubayakuliner160419004.R
import com.ubaya.ubayakuliner160419004.model.Menu
import com.ubaya.ubayakuliner160419004.util.loadImage
import kotlinx.android.synthetic.main.menu_list_item.view.*

class MenuListAdapter (val menuList:ArrayList<Menu>) : RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    class MenuViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_list_item, parent, false)
        return MenuListAdapter.MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        with(holder.view) {
            textNameMenu.text = menu.name
            textPriceMenu.text = "Rp" + menu.price + ",-"
            imageMenu.loadImage(menu.photo, progressImageMenu)
            buttonDetailMenu.setOnClickListener {
                val action = MenuFragmentDirections.actionMenuFragmentToMenuDetailFragment(menu.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = menuList.size

    fun updateMenuList(newMenuList: ArrayList<Menu>)
    {
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }
}