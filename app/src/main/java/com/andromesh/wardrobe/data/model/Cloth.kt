package com.andromesh.wardrobe.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "cloth")
data class Cloth(


        @PrimaryKey
        @field:SerializedName("filePath")
        val filePath: String,

        @field:SerializedName("Type")
        val type: String

) {
    override fun toString(): String {
        return "Cloth(filePath='$filePath', type='$type')"
    }

    companion object {

        enum class ClothTypes {
            shirt, trouser
        }

    }
}