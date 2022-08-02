package com.example.random2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list=findViewById<Button>(R.id.list)
        val generate=findViewById<Button>(R.id.random)
        list.setOnClickListener{
            val intent= Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
        generate.setOnClickListener{
            val intent=Intent(this,NumberActivity::class.java)
            startActivity(intent)
        }
    }
}