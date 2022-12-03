package com.example.homework.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R

/**
 * 首页上方筛选功能
 */
class ShopHeaderAdapter: RecyclerView.Adapter<ShopHeaderAdapter.ShopHeaderViewHolder>() {
    class ShopHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val shopKind1TextView: TextView = itemView.findViewById(R.id.kind1)
        private val shopKind2TextView: TextView = itemView.findViewById(R.id.kind2)
        private val shopKind3TextView: TextView = itemView.findViewById(R.id.kind3)
        private val shopKind4TextView: TextView = itemView.findViewById(R.id.kind4)
        private val shopKind5TextView: TextView = itemView.findViewById(R.id.kind5)

        fun bind(){
            shopKind1TextView.text = "待开发"
            shopKind2TextView.text = "待开发"
            shopKind3TextView.text = "待开发"
            shopKind4TextView.text = "待开发"
            shopKind5TextView.text = "待开发"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shoplist_header_item,parent,false)
        return ShopHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopHeaderViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 1
    }
}