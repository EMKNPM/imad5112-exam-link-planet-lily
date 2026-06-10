package com.example.campsitecommander

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


    //declaring our variables and arrays
     // these are the parallel arrays that will match the existing variables

       private val maxItems = 20
    private  val  itemName = Array(maxItems){ " "}
    private  val categories  = Array(maxItems){ " "}
    private val comments= Array(maxItems){ " "}
    private val quantity = IntArray(maxItems) { 0 }

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


        //When the button add gear is pressed an item is added to the list
        btnAdd.setOnClickListener {
          if ( currentSize >= maxItems) {
              Toast.makeText(this ,"Cannot add more items", Toast.LENGTH_SHORT).show()
              return@setOnClickListener
          }
            //Gets entered by the user
            val item = edtItemName.text.toString()
            val cat = edtCategory.text.toString()
            val com = edtComment.text.toString()
            val quan = edtQuantity.text.toString()

            if (item.isEmpty() || cat.isEmpty() || com.isEmpty() || quan.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            itemName[currentSize] = item
            categories[currentSize]= cat
            comments[currentSize] = com
            quantity[currentSize] = quan

            currentSize++

            // Clearing the inputs so that the user can enter the next set of data
            edtItemName.text.clear()
            edtQuantity.text.clear()
            edtComment.text.clear()
            edtCategory.text.clear()

            btnNext.setOnClickListener {
                if (currentSize == 0){
                    Toast.makeText(this,"Please enter an item to the list.", Toast.LENGTH_LONG).show()
                }
                val Quantity = quan.toIntOrNull()
                if (Quantity == null || Quantity < 1){

                }
                btnMain.setOnClickListener {
                    finishAffinity()
                }

            }






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}}