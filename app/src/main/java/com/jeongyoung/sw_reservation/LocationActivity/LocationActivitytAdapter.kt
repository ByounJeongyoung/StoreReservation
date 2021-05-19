package com.jeongyoung.sw_reservation.LocationActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jeongyoung.sw_reservation.MainActivity
import com.jeongyoung.sw_reservation.MainFragment.FragmentActivity
import com.jeongyoung.sw_reservation.StoreReservationActivity
import com.jeongyoung.sw_reservation.StoreReservationDetailActivity
import com.jeongyoung.sw_reservation.databinding.LocationFragmentSheetDetailBinding


class LocationActivityAdapter(val storeName: ArrayList<Store>) :
    RecyclerView.Adapter<LocationActivityAdapter.Holder>() {

    inner class Holder(val binding: LocationFragmentSheetDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                //  Toast.makeText(this@LocationActivityAdapter,"${binding.storeName.text}",Toast.LENGTH_SHORT).show()
                val intent = Intent(binding.root.context, StoreReservationDetailActivity::class.java)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }

        fun bind(store: Store) {

            binding.storeName.text = store.name
            binding.piceTextView.text = store.price
            binding.titlefood.text = store.titlefood
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationActivityAdapter.Holder {
        return Holder(
            LocationFragmentSheetDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationActivityAdapter.Holder, position: Int) {
        val list = storeName.get(position)
        holder.bind(list)
    }

    override fun getItemCount() = storeName.size
}
/*
class LocationActivtyAdapter() : ListAdapter<Item,LocationActivtyAdapter.Holder>(differ) {
    //


    inner class Holder(val binding: LocationFragmentSheetDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            var storename = binding.storeName
            var price = binding.piceTextView

            storename.text = item.name
            price.text = item.price
        }


        // fun bind(store: String) {
        //     binding.storeName.text = store
        //}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LocationFragmentSheetDetailBinding.inflate(LayoutInflater.from(parent.context), parent,
            false
            )
        )
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        //val store = StoreModel.get(position)
        holder.bind(currentList[position])
    }

    companion object{
        val differ = object: DiffUtil.ItemCallback<Item>(){
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

}*/