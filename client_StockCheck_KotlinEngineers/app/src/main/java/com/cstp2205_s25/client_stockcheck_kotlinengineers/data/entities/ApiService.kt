package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiService {
    private const val BASE_URL = "http://10.0.2.2:5000"  //Android emulator localhost
// Signup user
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

            BufferedOutputStream(conn.outputStream).use {
                it.write(
                    postData.toString().toByteArray()
                )
            }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 201 // return true if user created successfully
        }
    }
// Login user
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

            BufferedOutputStream(conn.outputStream).use {
                it.write(
                    postData.toString().toByteArray()
                )
            }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 200 // true if login success
        }
    }
    // Get all locations
    suspend fun getAllLocations(): List<Location> {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/locations")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val locations = mutableListOf<Location>()

            if (conn.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = reader.readText()
                reader.close()

                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    locations.add(
                        Location(
                            id = obj.getString("_id"),
                            obj.getString("name"),
                            obj.getString("address"),
                            obj.getString("contactName"),
                            obj.getString("contactEmail"),
                            obj.optString("contactPhone"),
                        )
                    )
                }
            }

            conn.disconnect()
            locations
        }
    }
    // Create new location
    suspend fun createLocation(location: Location): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/locations")
            val postData = JSONObject().apply {
                put("name", location.name)
                put("address", location.address)
                put("contactName", location.contactName)
                put("contactEmail", location.contactEmail)
                put("contactPhone", location.contactPhone)
            }

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            BufferedOutputStream(conn.outputStream).use {
                it.write(postData.toString().toByteArray())
            }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 201 // Assuming 201 Created is returned on success
        }
    }

    // Edit a location
    suspend fun editLocation(location: Location): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/locations/${location.id}")
            val putData = JSONObject().apply {
                put("name", location.name)
                put("address", location.address)
                put("contactName", location.contactName)
                put("contactEmail", location.contactEmail)
                put("contactPhone", location.contactPhone)
            }

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "PUT"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true

            BufferedOutputStream(conn.outputStream).use {
                it.write(putData.toString().toByteArray())
            }

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 200 // true if update successful
        }
    }
// Delete a location
    suspend fun deleteLocation(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/locations/$id")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "DELETE"

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 200 // true if deletion was successful
        }
    }

}