@file:Suppress("DEPRECATION")

package com.example.asynctask

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class GetAsyncTask : AsyncTask<Void?, Void?, String>() {
    private var activity: MainActivity? = null
    private var asyncCallBack: AsyncCallBack? = null
    private var progressBar: ProgressBar? = null
    fun setInstance(context: Context?, progressBar: ProgressBar?): GetAsyncTask {
        activity = context as MainActivity?
        asyncCallBack = context
        this.progressBar = progressBar
        return this
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Void?): String {
        val httpGet = HttpGet("https://simplifiedcoding.net/demos/marvel")
        val client: HttpClient = HttpClientBuilder.create().build()//DefaultHttpClient()
        var result = ""
        try {
            Log.d("TAG", "1")
            val response = client.execute(httpGet)
            Log.d("TAG", "2")
            val statusCode = response.statusLine.statusCode
            Log.d("TAG", "3")
            Log.d("TAG", statusCode.toString())
            if (statusCode == 200) {
                val inputStream = response.entity.content
                val reader = BufferedReader(InputStreamReader(inputStream))
                result = reader.readText()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            activity!!.runOnUiThread { progressBar!!.visibility = View.GONE }
        }
        return result
    }

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
        progressBar!!.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Log.d("OnPostExecute: ", result)
        asyncCallBack!!.setResult(result)
    }
}