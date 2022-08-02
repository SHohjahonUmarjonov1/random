package com.example.random2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    private val items = mutableSetOf<String>()
    private val adapter = ItemAdapter{
        items.remove(it)
        return@ItemAdapter items

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val add = findViewById<Button>(R.id.add)
        val shuffle = findViewById<Button>(R.id.shuffle)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = adapter
        add.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val ll = LinearLayout(this)
            val lp = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
                1F,
            )
            val editText = EditText(this)
            editText.layoutParams = lp
            ll.addView(editText)
            editText.hint = "Elementlarni kiriting"
            builder.setView(ll)
            builder.setPositiveButton("OK") { dialog, _ ->
                val item = editText.text.toString().split("\n")
                this.items.addAll(item)
                adapter.submitList(this.items.toList())
                dialog.dismiss()
            }
            builder.show()
        }
        shuffle.setOnClickListener {
            adapter.submitList(this.items.shuffled())
        }
    }
}