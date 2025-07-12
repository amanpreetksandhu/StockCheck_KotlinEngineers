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

            // Example: Inventory item added
            socket.on("itemAdded") { args ->
                if (args.isNotEmpty()) {
                    val data = args[0] as JSONObject
                    val itemName = data.optString("name", "An item")
                    notificationHelper.showNotification("Item Added", "$itemName was added to inventory.")
                }
            }

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