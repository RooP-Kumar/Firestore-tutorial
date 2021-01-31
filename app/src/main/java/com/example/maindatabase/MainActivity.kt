package com.example.maindatabase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    lateinit var savebtn: Button
    lateinit var user_name: EditText
    lateinit var user_phone: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savebtn = findViewById(R.id.send_to_database)
        user_name = findViewById(R.id.user_name)
        user_phone = findViewById(R.id.user_phone)
        val db = FirebaseFirestore.getInstance()

        savebtn.setOnClickListener {

            val name: String = user_name.text.toString()
            val phone = user_phone.text.toString()

            var data: MutableMap<String, Any> = HashMap()
            data["Phone"] = phone
            data["User name"] = name

            db.collection("Users")
                .document(name)
                .set(data)
                .addOnSuccessListener {
                    Toast.makeText(this, "Successfully added on database", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }


        }

    }
}