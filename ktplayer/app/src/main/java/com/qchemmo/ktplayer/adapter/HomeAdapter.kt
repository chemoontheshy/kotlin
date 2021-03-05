package com.qchemmo.ktplayer.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qchemmo.ktplayer.widget.HomeItemView

/**
 * @ClassName: HomeAdapter
 * @Description:
 * @Author: chemoontheshy
 * @Date: 2021/3/5-11:03
 */
class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    class HomeHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(HomeItemView(parent?.context))
        }

    override fun getItemCount(): Int {
        return 20
     }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {

     }
}