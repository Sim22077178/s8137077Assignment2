package com.example.s8137077assignment2.details

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.s8137077assignment2.R
import com.google.gson.Gson
import com.google.gson.JsonObject

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)

        val container =
            findViewById<LinearLayout>(R.id.detailsContainer)

        val entityJson =
            intent.getStringExtra("entity")

        if (entityJson.isNullOrEmpty()) {
            finish()
            return
        }

        val entity =
            Gson().fromJson(
                entityJson,
                JsonObject::class.java
            )

        // Dynamically create a TextView for every property returned
        // by the API, including the full description.
        entity.entrySet().forEach { entry ->

            val textView = TextView(this)

            textView.text =
                "${entry.key}: ${entry.value}"

            textView.textSize = 18f

            textView.setPadding(
                16,
                16,
                16,
                16
            )

            container.addView(textView)
        }
    }
}