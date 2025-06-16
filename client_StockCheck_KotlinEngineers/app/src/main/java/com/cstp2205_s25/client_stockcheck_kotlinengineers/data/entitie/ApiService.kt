package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entitie

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.net.HttpURLConnection
import java.net.URL

object ApiService {
    private const val BASE_URL = "http://10.0.2.2:5000"  //Android emulator localhost

    suspend fun signup(email: String, employeeId: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/signup")
            val postData = JSONObject()
            postData.put("email", email)
            postData.put("employeeId", employeeId)
            postData.put("password", password)

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            BufferedOutputStream(conn.outputStream).use { it.write(postData.toString().toByteArray()) }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 201 // return true if user created successfully
        }
    }

    suspend fun login(employeeId: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/login")
            val postData = JSONObject()
            postData.put("employeeId", employeeId)
            postData.put("password", password)

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            BufferedOutputStream(conn.outputStream).use { it.write(postData.toString().toByteArray()) }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 200 // true if login success
        }
    }
}