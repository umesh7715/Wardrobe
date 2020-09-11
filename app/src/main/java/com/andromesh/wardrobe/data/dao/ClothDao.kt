package com.andromesh.wardrobe.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andromesh.wardrobe.data.model.Cloth
import com.andromesh.wardrobe.data.model.FavaoriteCombination


@Dao
interface ClothDao {

    @Query("SELECT * FROM cloth WHERE type = :clothType")
    fun getCloths(clothType: String): List<Cloth>

    @Query("SELECT * FROM favaoritecombination WHERE isFavorite = 1")
    fun getFavaoriteCombinationList(): List<FavaoriteCombination>

    @Query("SELECT * FROM favaoritecombination WHERE isFavorite = 1 AND filePathCombination =:combination ")
    fun getFavaoriteCombination(combination: String): FavaoriteCombination

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cloth: Cloth)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCombination(favaoriteCombination: FavaoriteCombination)

}