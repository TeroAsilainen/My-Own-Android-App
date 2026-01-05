package com.example.datemanager.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface DbItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<DbItem>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<DbItem?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: DbItem)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: DbItem)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: DbItem)
}