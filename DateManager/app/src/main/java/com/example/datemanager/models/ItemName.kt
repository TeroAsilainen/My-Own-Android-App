package com.example.datemanager.models


import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ItemName(
    var product: JsonObject
)
