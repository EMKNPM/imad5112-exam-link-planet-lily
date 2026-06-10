package com.example.campsitecommander

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.StringBuilder
import java.util.Locale

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)

        //Typecasting for the detailed screen
        val tvDisplay= findViewById<TextView>(R.id.tvDisplay)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnList = findViewById<Button>(R.id.btnList)

           val itemName = intent.getStringArrayExtra("EXTRA_ITEMS")
            val categories = intent.getStringArrayExtra("EXTRA_CATEGORIES")
        val quantities = intent.getStringArrayExtra("EXTRA_QUANTITIES")
        val comment = intent.getStringArrayExtra("EXTRA_COMMENTS")

        btnList.setOnClickListener {
            val builder = StringBuilder()
            var matchCount = 0

            if (itemName != null && categories != null && quantities != null && comment != null) {
                builder.append("=== ALL PACKING ITEMS ===\n\n")

                for (i in itemName.indices) {
                    builder.append("• ${itemName[i]} [${categories[i]}]\n")
                    builder.append("  Qty: ${quantities[i]} | Note: ${comment[i]}\n")
                    builder.append("----------------------------------------\n")
                    matchCount++
                }

                if (matchCount == 0) {
                    builder.append("No items found in your packing list.")
                }
            } else {
                builder.append("Error: Data did not arrive safely from Screen One.")
            }

            // This updates the view ONLY when the button is pressed
            tvDisplay.text = builder.toString()



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}}