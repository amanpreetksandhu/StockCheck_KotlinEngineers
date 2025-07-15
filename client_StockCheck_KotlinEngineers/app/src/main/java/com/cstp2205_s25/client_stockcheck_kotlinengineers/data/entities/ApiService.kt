package com.cstp2205_s25.client_stockcheck_kotlinengineers.data.entities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.File
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object ApiService {
    private const val BASE_URL = "http://10.0.2.2:5000"  //Android emulator localhost; AKA backend URL

// Signup user
    suspend fun signup(email: String, employeeId: String, password: String): Boolean {
        return withContext(Dispatchers.IO) { // this makes sure the network call runs in the background and doesn't slow down or freeze the main screen.
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
                            obj.getString("city"),
                            obj.getString("country"),
                            obj.getString("contactName"),
                            obj.getString("contactPosition"),
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
                put("city", location.city)
                put("country", location.country)
                put("contactName", location.contactName)
                put("contactPosition", location.contactPosition)
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
                put("city", location.city)
                put("country", location.country)
                put("contactName", location.contactName)
                put("contactPosition", location.contactPosition)
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
    //==========================================END OF LOCATION METHODS============================/

    // ======================================= INVENTORY METHODS ==================================/

    //Get all items
    suspend fun getAllInventoryItems(): List<InventoryItem> {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/inventory")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val items = mutableListOf<InventoryItem>()

            if (conn.responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = reader.readText()
                reader.close()

                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    items.add(
                        InventoryItem(
                            id = obj.getString("_id"),
                            name = obj.getString("name"),
                            description = obj.optString("description", ""),
                            category = obj.optString("category", ""),
                            qty = if (obj.has("qty")) obj.optInt("qty") else 0,
                            price = obj.optDouble("price", 0.0),
                            imageUrl = obj.optString("imageUrl", null),
                            locationId = if (obj.has("locationId")) obj.getString("locationId") else null,
                            status = obj.optString("status", null)
                        )
                    )
                }
            }

            conn.disconnect()
            items
        }
    }


    // Add new item
    suspend fun addInventoryItem(item: InventoryItem): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/inventory")
            val postData = JSONObject().apply {
                put("name", item.name)
                put("description", item.description)
                put("category", item.category)
                put("qty", item.qty)
                put("price", item.price)
                put("imageUrl", item.imageUrl)
                put("locationId", item.locationId)
                put("status", item.status)
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
            responseCode == 201
        }
    }


    // Edit item

    suspend fun editInventoryItem(item: InventoryItem): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/inventory/${item.id}")
            val putData = JSONObject().apply {
                put("name", item.name)
                put("description", item.description)
                put("category", item.category)
                put("qty", item.qty)
                put("price", item.price)
                put("imageUrl", item.imageUrl)
                put("locationId", item.locationId)
                put("status", item.status)
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
            responseCode == 200
        }
    }


    // Delete item
    suspend fun deleteInventoryItem(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/inventory/$id")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "DELETE"

            val responseCode = conn.responseCode
            conn.disconnect()
            responseCode == 200
        }
    }

    // Image logic

    suspend fun uploadImage(imageFile: File): String? {
        return withContext(Dispatchers.IO) {
            val url = URL("$BASE_URL/api/upload")
            val boundary = "Boundary-" + System.currentTimeMillis()
            val lineEnd = "\r\n"
            val twoHyphens = "--"

            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Connection", "Keep-Alive")
            conn.setRequestProperty("Cache-Control", "no-cache")
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=$boundary")
            conn.doOutput = true
            conn.doInput = true

            val outputStream = DataOutputStream(conn.outputStream)

            outputStream.writeBytes(twoHyphens + boundary + lineEnd)
            outputStream.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"${imageFile.name}\"$lineEnd")
            outputStream.writeBytes("Content-Type: image/jpeg$lineEnd")
            outputStream.writeBytes(lineEnd)

            val fileBytes = imageFile.readBytes()
            outputStream.write(fileBytes)

            outputStream.writeBytes(lineEnd)
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd)
            outputStream.flush()
            outputStream.close()

            val responseCode = conn.responseCode

            if (responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(conn.inputStream))
                val response = reader.readText()
                reader.close()

                // FIXED: Get "imageUrl" instead of "url"
                val json = JSONObject(response)
                json.optString("imageUrl", null)
            } else {
                null
            }.also {
                conn.disconnect()
            }
        }
    }











}
