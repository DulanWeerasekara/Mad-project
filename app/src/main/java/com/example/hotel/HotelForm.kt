package com.example.hotel

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hotel.databinding.FragmentHotelFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class HotelForm : Fragment() {
    lateinit var hotelName:String
    lateinit var hotelAddress:String
    lateinit var hotelEmail:String
    lateinit var hotelPhone:String
    lateinit var hotelPrice:String

    lateinit var dataBase: DatabaseReference
    private lateinit var binding: FragmentHotelFormBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    
    ): View? {
        //
        // Inflate the layout for this fragment
        binding=FragmentHotelFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myBundle=arguments
        hotelName= myBundle?.getString("hotelName").toString()

        binding.hotelRegButton.setOnClickListener{




            hotelName=binding.hotelName.text.toString()
            hotelAddress=binding.hotelAddress.text.toString()
            hotelEmail=binding.hotelEmail.text.toString()
            hotelPhone=binding.hotelPhone.text.toString()
            hotelPrice=binding.hotelPrice.text.toString()

            if(TextUtils.isEmpty(hotelName.toString())){
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            }else
            if(TextUtils.isEmpty(hotelAddress.toString())){
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            }else if(TextUtils.isEmpty(hotelEmail.toString())){
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            }else if(TextUtils.isEmpty(hotelPhone.toString())){
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            }else if(TextUtils.isEmpty(hotelPrice.toString())){
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            }else
            {

            saveHotelInDatabase(hotelName,hotelAddress,hotelEmail,hotelPhone,hotelPrice)}
        }

    }
    private fun saveHotelInDatabase(hotelName:String,hotelAddress:String,hotelEmail:String,hotelPhone:String,hotelPrice:String){
        val hotelData=HotelData(hotelName,hotelAddress,hotelEmail,hotelPhone,hotelPrice)
        dataBase= FirebaseDatabase.getInstance().getReference("Hotel")
        var childValue=hotelName
        dataBase.child(childValue).setValue(hotelData).addOnSuccessListener{
            Toast.makeText(activity, "complete", Toast.LENGTH_SHORT).show()
        }
    }





}