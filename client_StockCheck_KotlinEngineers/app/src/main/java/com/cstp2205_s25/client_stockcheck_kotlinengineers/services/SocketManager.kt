package com.cstp2205_s25.client_stockcheck_kotlinengineers.services

import android.content.Context
import android.util.Log
import com.cstp2205_s25.client_stockcheck_kotlinengineers.utils.NotificationHelper
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject

object SocketManager {

    private lateinit var socket: Socket
    private var isInitialized = false

    fun init(context: Context) {
        if (isInitialized) return

        try {
            socket = IO.socket("http://10.0.2.2:5000") // 🛠 Replace with your actual backend URL
            socket.connect()
            isInitialized = true

            val notificationHelper = NotificationHelper(context)

            // ========================================== Items notifications Block  ================================================\\
            // Notify when an item is added
            socket.on("itemAdded") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val itemName = data.optString("name", "An item")
                    notificationHelper.showNotification("Item Added", "$itemName was added to inventory.")
                }
            }

            // Notify when an item is edited
            socket.on("itemUpdated") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val itemName = data.optString("name", "An item")
                    notificationHelper.showNotification("Item Info Updated", "$itemName was updated in inventory.")
                }
            }


            // Notify when an item is deleted
            socket.on("itemDeleted") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val itemName = data.optString("name", "An item")
                    notificationHelper.showNotification("Item Deleted", "$itemName was removed from inventory.")
                }
            }



            // Low stock

            //======================================= Items notifications Block ====================================================/

            //======================================= Locations notifications Block ====================================================/

            // Notify when a location is added
            socket.on("locationAdded") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val locationName = data.optString("name", "A location")
                    notificationHelper.showNotification("Location Added", "$locationName was added.")
                }
            }

            // Notify when a location is updated
            socket.on("locationUpdated") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val locationName = data.optString("name", "A location")
                    notificationHelper.showNotification("Location Info Updated", "$locationName was updated.")
                }
            }

            // Notify when a location is deleted
            socket.on("locationDeleted") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val locationName = data.optString("name", "A location")
                    notificationHelper.showNotification("Location Deleted", "$locationName was deleted.")
                }
            }



            // ========================================== Locations notifications Block  ================================================/

            // More events can be added here (e.g., itemEdited, itemDeleted)
        } catch (e: Exception) {
            Log.e("SocketManager", "Error initializing socket: ${e.message}")
        }
    }

    fun disconnect() {
        if (::socket.isInitialized && socket.connected()) {
            socket.disconnect()
        }
    }
}