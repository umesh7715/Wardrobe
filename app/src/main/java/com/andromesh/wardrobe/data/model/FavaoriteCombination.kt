package com.andromesh.wardrobe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favaoritecombination")
data class FavaoriteCombination(


        @PrimaryKey
        @field:SerializedName("filePathCombination")
        val filePathCombination: String,

        @field:SerializedName("isFavorite")
        val isFavorite: Boolean

) {

    override fun toString(): String {
        return "FavaoriteCombination(filePathCombination='$filePathCombination', isFavorite=$isFavorite)"
    }
}