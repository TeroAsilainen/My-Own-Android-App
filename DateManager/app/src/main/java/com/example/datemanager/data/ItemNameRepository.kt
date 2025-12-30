package com.example.datemanager.data

import android.util.Log
import com.example.datemanager.models.ItemName
import com.example.datemanager.network.ItemNameClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ItemNameRepository {
    private val client = ItemNameClient.client

    suspend fun getItemName(eanCode: String): ItemName {
        Log.d("INRepository", "https://world.openfoodfacts.net/api/v2/product/$eanCode")

        return client.get("https://world.openfoodfacts.net/api/v2/product/$eanCode").body()
    }
}