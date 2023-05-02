package com.example.hotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button1 = findViewById<Button>(R.id.button1)
        var button2 = findViewById<Button>(R.id.button2)


        button1.setOnClickListener{
            val intent1= Intent(this, hotelListPage::class.java)
            startActivity(intent1)

        }
        button2.setOnClickListener{
            val intent2= Intent(this, hotelBookListPage::class.java)
            startActivity(intent2)

        }






    }
}