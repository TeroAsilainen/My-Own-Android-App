package com.example.datemanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: DbItem)

    @Update
    suspend fun update(item: DbItem)

    @Delete
    suspend fun delete(item: DbItem)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<DbItem>

    @Query("SELECT * from items ORDER BY expDate ASC")
    fun getAllItems(): Flow<List<DbItem>>
}