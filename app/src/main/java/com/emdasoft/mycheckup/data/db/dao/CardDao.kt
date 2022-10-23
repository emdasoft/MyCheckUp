package com.emdasoft.mycheckup.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.emdasoft.mycheckup.domain.CardItem

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCard(card: CardItem)

    @Delete
    suspend fun removeCard(card: CardItem)

    @Query("SELECT * FROM cards")
    suspend fun getCardList(): LiveData<List<CardItem>>
}