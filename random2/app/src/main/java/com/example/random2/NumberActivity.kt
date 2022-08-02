package com.example.random2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class NumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)
        val executor=Executors.newSingleThreadScheduledExecutor()
        val button=findViewById<Button>(R.id.generate)
        val from=findViewById<EditText>(R.id.from)
        val to=findViewById<EditText>(R.id.to)
        val number=findViewById<TextView>(R.id.number)
        button.setOnClickListener{
            val fromvalue=if (from.text.isNotBlank()){
                from.text.toString().toInt()
            }else{
                from.setText("0")
                0
            }
            val tovalue=if (to.text.isNotBlank()){
                to.text.toString().toInt()
            }else{
                val random=Random(System.currentTimeMillis()).nextInt(10,101)
                to.setText(random.toString())
                random
            }
            fun set(){
                val random= Random(System.currentTimeMillis()).nextInt(fromvalue, tovalue)
                number.text=random.toString()
            }
            for (i in 1..20){
                executor.schedule(::set,i*50L,TimeUnit.MILLISECONDS)
            }
        }
    }
}