package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity(){
    private val maxItems = 25
    private val itemName = Array(maxItems) { "" }
    private val categories = Array(maxItems) { "" }
    private val quantities = IntArray(maxItems) { 0 }
    private val comment = Array(maxItems) { "" }

    private var currentSize = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        //typecasting
        val edtItemName = findViewById<EditText>(R.id.edtItemName)
        val edtCategory = findViewById<EditText>(R.id.edtCategory)
        val edtQuantity = findViewById<EditText>(R.id.edtQuantity)
        val edtComment = findViewById<EditText>(R.id.edtComments)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnMain = findViewById<Button>(R.id.btnMain)
        val btnNext = findViewById<Button>(R.id.btnNext )

        btnAdd.setOnClickListener {
            if (currentSize >= maxItems) {
                Toast.makeText(this, "List is full!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = edtItemName.text.toString()
            val cat = edtCategory.text.toString()
            val quan = edtQuantity.text.toString()
            val com = edtComment.text.toString()

            if (item.isEmpty() || cat.isEmpty() || quan.isEmpty() || com.isEmpty()) {
                Toast.makeText(this, "Error: All fields must be filled out.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            itemName[currentSize] = item
            categories[currentSize] = cat
            quantities[currentSize] = quan
            comment[currentSize] = com

            currentSize++

            edtItemName.text.clear()
            edtCategory.text.clear()
            edtQuantity.text.clear()
            edtComment.text.clear()

            if (currentSize == 0) {
                Toast.makeText(this, "The list is empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            val finalItems = itemName.copyOfRange(0, currentSize)
            val finalCategories = categories.copyOfRange(0, currentSize)
            val finalQuantities = quantities.copyOfRange(0, currentSize)
            val finalComments = comment.copyOfRange(0, currentSize)

            val intent = Intent(this, MainActivity3::class.java).apply {
                putExtra("EXTRA_ITEMS", finalItems)
                putExtra("EXTRA_CATEGORIES", finalCategories)
                putExtra("EXTRA_QUANTITIES", finalQuantities)
                putExtra("EXTRA_COMMENTS", finalComments)

            }
            startActivity(intent)
        }
        btnMain.setOnClickListener {
            finishAffinity()
        }














        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}