package com.example.homework.shoplist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.homework.R
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.data.Shop


class ShopsAdapter(private val onClick:(Shop)->Unit) :
    ListAdapter<Shop,ShopsAdapter.ShopViewHolder>(ShopDiffCallback) {

    class ShopViewHolder(itemView: View,val onClick: (Shop) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val shopTextView: TextView = itemView.findViewById(R.id.shopName)
        private val shopImageView: ImageView = itemView.findViewById(R.id.shopImage)
        private var shopScore: TextView = itemView.findViewById(R.id.shopScore)
        private var saleNumber: TextView = itemView.findViewById(R.id.saleNunber)

        private var currentShop: Shop? = null

        init {
            itemView.setOnClickListener {
                currentShop?.let {
                    onClick(it)
                }
            }
        }
        fun bind(shop: Shop) {
            currentShop = shop
            shopTextView.text = shop.name
            shopScore.text = shop.score.toString() + "分"
            saleNumber.text = "销售量 " + shop.saleNumber.toString()
            if (shop.image != null){
                shopImageView.setImageResource(shop.image)
            }else{
                shopImageView.setImageResource(R.drawable.rose)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_item, parent, false)
        return ShopViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = getItem(position)
        holder.bind(shop)
    }

}

object ShopDiffCallback : DiffUtil.ItemCallback<Shop>() {
    override fun areItemsTheSame(oldItem: Shop, newItem: Shop): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Shop, newItem: Shop): Boolean {
        return oldItem.id == newItem.id
    }
}