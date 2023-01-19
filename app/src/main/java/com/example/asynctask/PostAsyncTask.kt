@file:Suppress("DEPRECATION")

package com.example.asynctask

import android.content.Context
import android.os.AsyncTask
import android.view.View
import android.widget.ProgressBar
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.URL

class PostAsyncTask : AsyncTask<String?, Void?, String>() {
    private var activity: MainActivity? = null
    private var asyncCallBack: AsyncCallBack? = null
    private var progressBar: ProgressBar? = null
    fun setInstance(context: Context?, progressBar: ProgressBar?): PostAsyncTask {
        activity = context as MainActivity?
        asyncCallBack = context
        this.progressBar = progressBar
        return this
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String {
        var response = ""
        var reader: BufferedReader? = null
        try {
            val url = URL("https://reqres.in/api/users")
            val data = params[0]
            val conn = url.openConnection()
            conn.doOutput = true
            val wr = OutputStreamWriter(conn.getOutputStream())
            wr.write(data)
            wr.flush()
            reader = BufferedReader(InputStreamReader(conn.getInputStream()))
            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append("""$line""".trimIndent())
            }
            response = sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                activity!!.runOnUiThread { progressBar!!.visibility = View.GONE }
                reader!!.close()
            } catch (_: Exception) {
            }
        }
        return response
    }

    @Deprecated("Deprecated in Java")
    override fun onPreExecute() {
        super.onPreExecute()
        progressBar!!.visibility = View.VISIBLE
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        asyncCallBack!!.setResult(result)
    }
}