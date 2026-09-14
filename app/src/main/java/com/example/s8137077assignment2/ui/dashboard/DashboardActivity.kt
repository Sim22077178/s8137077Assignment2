package com.example.s8137077assignment2.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.s8137077assignment2.R
import com.example.s8137077assignment2.details.DetailsActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModel()

    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        // Retrieve the keypass passed from LoginActivity.
        val keypass =
            intent.getStringExtra("keypass")

        // Without a valid keypass there is no way to retrieve dashboard data.

        if (keypass.isNullOrEmpty()) {
            finish()
            return
        }

        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerView)

        val progressBar =
            findViewById<ProgressBar>(R.id.dashboardProgress)

        val totalText =
            findViewById<TextView>(R.id.tvEntityTotal)

        val errorText =
            findViewById<TextView>(R.id.tvDashboardError)

        // Create the RecyclerView adapter.
        // Clicking an entity opens the Details screen.
        adapter = EntityAdapter(emptyList()) { entity ->

            // Convert the selected entity into JSON so it can be
            // passed safely to DetailsActivity.
            val entityJson =
                Gson().toJson(entity)

            val intent = Intent(
                this,
                DetailsActivity::class.java
            )

            intent.putExtra(
                "entity",
                entityJson
            )

            startActivity(intent)
        }

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        // Start loading the entities from the API.
        viewModel.loadDashboard(keypass)

        lifecycleScope.launch {

            viewModel.isLoading.collect { loading ->

                progressBar.visibility =
                    if (loading) View.VISIBLE else View.GONE
            }
        }

        lifecycleScope.launch {

            viewModel.error.collect { error ->

                errorText.text = error ?: ""
            }
        }

        lifecycleScope.launch {

            viewModel.dashboard.collect { dashboard ->

                dashboard?.let {

                    adapter.updateData(it.entities)

                    totalText.text =
                        "Total entities: ${it.entityTotal}"
                }
            }
        }
    }
}