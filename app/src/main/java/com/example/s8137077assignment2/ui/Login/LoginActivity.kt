package com.example.s8137077assignment2.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.s8137077assignment2.R
import com.example.s8137077assignment2.dashboard.DashboardActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val errorText = findViewById<TextView>(R.id.tvError)

        loginButton.setOnClickListener {

            viewModel.login(
                username.text.toString().trim(),
                password.text.toString()
            )
        }

        lifecycleScope.launch {

            viewModel.isLoading.collect { loading ->

                progressBar.visibility =
                    if (loading) View.VISIBLE else View.GONE

                loginButton.isEnabled = !loading
            }
        }

        lifecycleScope.launch {

            viewModel.error.collect { error ->

                errorText.text = error ?: ""
            }
        }

        lifecycleScope.launch {

            viewModel.loginSuccess.collect { keypass ->

                if (!keypass.isNullOrEmpty()) {

                    val intent = Intent(
                        this@LoginActivity,
                        DashboardActivity::class.java
                    )

                    intent.putExtra("keypass", keypass)

                    startActivity(intent)

                    finish()
                }
            }
        }
    }
}