package com.example.datemanager.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao): DbItemsRepository {
    override fun getAllItemsStream(): Flow<List<DbItem>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<DbItem?> = itemDao.getItem(id)

    override suspend fun insertItem(item: DbItem) = itemDao.insert(item)

    override suspend fun deleteItem(item: DbItem) = itemDao.delete(item)

    override suspend fun updateItem(item: DbItem) = itemDao.update(item)
}