package com.example.hotel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class hotelListAdapter(private val hotelList: ArrayList<HotelData>)
    :RecyclerView.Adapter<hotelListAdapter.hotelListViewHolder>(){


    private lateinit var mlistener:onItemClickListener


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mlistener=clickListener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hotelListViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.hotellistlayout,parent,false)
        return hotelListViewHolder(itemView,mlistener)

    }

    override fun getItemCount(): Int {
        return hotelList.size;
    }

    override fun onBindViewHolder(holder: hotelListViewHolder, position: Int) {
        val currentItem =hotelList[position]

        holder.hotelName.text=currentItem.hotelName

        holder.hotelDistrict.text=currentItem.hotelDistrict

        holder.hotelPrice.text=currentItem.hotelPrice
        holder.hotelImage.setImageBitmap(createImageBitMap((currentItem.hotelImage)))
    }

    class hotelListViewHolder (itemView : View,clickListener: onItemClickListener) :RecyclerView.ViewHolder(itemView){

        val hotelName :TextView = itemView.findViewById(R.id.hotelListNametv)
        val hotelDistrict:TextView=itemView.findViewById(R.id.hotelListDistricttv)
        val hotelPrice :TextView = itemView.findViewById(R.id.hotelListPricetv)
        val hotelImage :ImageView = itemView.findViewById(R.id.hotelListImagetv)



        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)

            }
        }

    }
    private fun createImageBitMap(image: String?): Bitmap {
        var bytes=android.util.Base64.decode(image,android.util.Base64.DEFAULT)
        var bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.size)

        return bitmap
    }

}