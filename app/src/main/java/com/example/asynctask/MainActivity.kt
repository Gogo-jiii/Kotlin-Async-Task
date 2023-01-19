package com.example.asynctask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), AsyncCallBack {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetRequest.setOnClickListener {
            GetAsyncTask().setInstance(
                this@MainActivity,
                progressBar
            ).execute()
        }
        btnPostRequest.setOnClickListener {
            val name = "IT wala"
            PostAsyncTask().setInstance(this@MainActivity, progressBar).execute(name)
        }
    }

    override fun setResult(result: String?) {
        Log.d("RESULT: ", result!!)
        txtResultValue.text = result
    }
}