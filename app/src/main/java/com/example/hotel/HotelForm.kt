package com.example.hotel

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hotel.databinding.FragmentHotelFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.net.URI


class HotelForm : Fragment() {
    lateinit var hotelName: String
    lateinit var hotelAddress: String
    lateinit var hotelEmail: String
    lateinit var hotelPhone: String
    lateinit var hotelPrice: String
    lateinit var hotelDistrict: String
    lateinit var hotelImage: String


    var uri:Uri?=null


    lateinit var dataBase: DatabaseReference
    private lateinit var binding: FragmentHotelFormBinding
    private lateinit var storageReference: StorageReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        //
        // Inflate the layout for this fragment
        binding = FragmentHotelFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageReference = FirebaseStorage.getInstance().reference

        var myBundle = arguments
        hotelName = myBundle?.getString("hotelName").toString()

//        val activityResultLauncher=registerForActivityResult<Intent,ActivityResult>(
//            ActivityResultContracts.StartActivityForResult()){result->
//            if(result.resultCode==RESULT_OK){
//                val data= result.data
//                uri=data!!.data
//                binding.UploadsImageView.setImageURI(uri)
//
//            }
//
//        }
//        binding.uploadSomePicturesButton.setOnClickListener{
//            val photoPicker=Intent(Intent.ACTION_PICK)
//            photoPicker.type="image/*"
//            activityResultLauncher.launch(photoPicker)
//
//        }


//        binding.uploadSomePicturesButton.setOnClickListener {
//
//            var myFileIntent = Intent(Intent.ACTION_GET_CONTENT)
//            myFileIntent.setType("image/*")
//            ActivityResultLauncher.launch(myFileIntent)
//        }
        binding.uploadSomePicturesButton.setOnClickListener {
            val myFileIntent = Intent(Intent.ACTION_GET_CONTENT)
            myFileIntent.setType("image/*")
            resultLauncher.launch(myFileIntent)
        }

        binding.hotelRegButton.setOnClickListener {



            hotelName = binding.hotelName.text.toString()
            hotelAddress = binding.hotelAddress.text.toString()
            hotelEmail = binding.hotelEmail.text.toString()
            hotelPhone = binding.hotelPhone.text.toString()
            hotelPrice = binding.hotelPrice.text.toString()
            hotelDistrict = binding.hotelDistrict.text.toString()


            if (TextUtils.isEmpty(hotelName.toString())) {
                Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

            } else
                if (TextUtils.isEmpty(hotelAddress.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelEmail.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelPhone.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelPrice.toString())) {
                    Toast.makeText(activity, "Please Enter All Details", Toast.LENGTH_SHORT).show()

                } else if (TextUtils.isEmpty(hotelDistrict.toString())) {
                    android.widget.Toast.makeText(
                        activity,
                        "Please Enter All Details",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()

                } else

                {


                    saveHotelInDatabase(
                        hotelName,
                        hotelAddress,
                        hotelEmail,
                        hotelPhone,
                        hotelPrice,
                        hotelDistrict,
                        hotelImage
                    )
                }
        }

    }

    private fun saveHotelInDatabase(
        hotelName: String,
        hotelAddress: String,
        hotelEmail: String,
        hotelPhone: String,
        hotelPrice: String,
        hotelDistrict: String,
        hotelImage: String
    ) {

        val hotelData = HotelData(
            hotelName,
            hotelAddress,
            hotelEmail,
            hotelPhone,
            hotelPrice,
            hotelDistrict,
            hotelImage
        )
        dataBase = FirebaseDatabase.getInstance().getReference("Hotel")
        var childValue = hotelName
        dataBase.child(childValue).setValue(hotelData).addOnSuccessListener {
            Toast.makeText(activity, "complete", Toast.LENGTH_SHORT).show()
            resetInputFieldsAfterSubmission()
        }
    }
//    private fun saveData(){
//
//        val storageReference= FirebaseStorage.getInstance().reference.child("hotelImage")
//            .child(uri!!.lastPathSegment!!)
//
//
//        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot->
//            val uriTask=taskSnapshot.storage.downloadUrl
//            while (!uriTask.isComplete);
//            val urlImage=uriTask.result
//            hotelImage =urlImage.toString()
//
//        }
//
//
//
//    }


//    private val ActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result: ActivityResult ->
//        if (result.resultCode == AppCompatActivity.RESULT_OK) {
//            val uri = result.data!!.data
//            try {
//                val inputStream = activity?.contentResolver?.openInputStream(uri!!)
//                val myBitmap = BitmapFactory.decodeStream(inputStream)
//                val stream = ByteArrayOutputStream()
//                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//                val bytes = stream.toByteArray()
//                hotelImage = android.util.Base64.encodeToString(bytes, android.util.Base64.DEFAULT)
//                binding.UploadsImageView.setImageBitmap(myBitmap)
//                inputStream!!.close()
//                Toast.makeText(activity, "Image Selected", Toast.LENGTH_SHORT).show()
//
//            } catch (ex: Exception) {
//                Toast.makeText(activity, ex.message.toString(), Toast.LENGTH_SHORT).show()
//            }//end try-catch block
//        }//end if


//    }

    private fun resetInputFieldsAfterSubmission() {

        binding.hotelName.text.clear()
        binding.hotelAddress.text.clear()
        binding.hotelEmail.text.clear()
        binding.hotelPhone.text.clear()
        binding.hotelPrice.text.clear()
        binding.hotelDistrict.text.clear()

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //how to access drawable folder from fragment
        var myDrawable = activity?.resources?.getDrawable(R.drawable.baseline_upload_file_24)
        binding.UploadsImageView.setImageDrawable(myDrawable)
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null && data.data != null) {
                val imageUri = data.data
                if (imageUri != null) {
                    val imageRef = storageReference.child("hotelImages/${hotelName}_${System.currentTimeMillis()}.jpg")
                    val uploadTask = imageRef.putFile(imageUri)
                    uploadTask.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            imageRef.downloadUrl.addOnSuccessListener { uri ->
                                hotelImage = uri.toString()
                                binding.UploadsImageView.setImageURI(uri)
                            }.addOnFailureListener { exception ->
                                Toast.makeText(requireContext(), "Failed to upload image: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Failed to upload image: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to get image URI", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Failed to get image data", Toast.LENGTH_SHORT).show()
            }

        }
    }



}


