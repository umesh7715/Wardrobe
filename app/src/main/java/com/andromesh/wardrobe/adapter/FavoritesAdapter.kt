package com.andromesh.wardrobe.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.andromesh.wardrobe.R
import com.andromesh.wardrobe.data.model.FavaoriteCombination
import java.io.File


class FavoritesAdapter(private val favoriteCOmbinationList: List<FavaoriteCombination>) : RecyclerView.Adapter<FavoritesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ivFavoriteShirt: ImageView
        var ivFavoriteTrouser: ImageView


        init {
            ivFavoriteShirt = view.findViewById(R.id.ivFavoriteShirt)
            ivFavoriteTrouser = view.findViewById(R.id.ivFaviriteTrouser)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_list_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val favaoriteCombination = favoriteCOmbinationList[position]

        holder.ivFavoriteShirt.setImageURI(Uri.fromFile(File(favaoriteCombination.filePathCombination.split("<||>")[0])))
        holder.ivFavoriteTrouser.setImageURI(Uri.fromFile(File(favaoriteCombination.filePathCombination.split("<||>")[1])))

    }

    override fun getItemCount(): Int {
        return favoriteCOmbinationList.size
    }

}