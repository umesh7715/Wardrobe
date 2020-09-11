package com.andromesh.wardrobe.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andromesh.my_portfolio.di.Injectable
import com.andromesh.my_portfolio.di.injectViewModel
import com.andromesh.wardrobe.R
import com.andromesh.wardrobe.adapter.FavoritesAdapter
import com.andromesh.wardrobe.databinding.MyFavoriteFragmentBinding
import com.andromesh.wardrobe.ui.viewmodel.ClothViewModel
import com.elifox.legocatalog.ui.GridSpacingItemDecoration
import com.elifox.legocatalog.ui.VerticalItemDecoration
import javax.inject.Inject

class MyFavourites : Fragment(), Injectable {

    private lateinit var binding: MyFavoriteFragmentBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private val linearDecoration: RecyclerView.ItemDecoration by lazy {
        VerticalItemDecoration(
                resources.getDimension(R.dimen.margin_normal).toInt())
    }

    private lateinit var gridLayoutManager: GridLayoutManager
    private val gridDecoration: RecyclerView.ItemDecoration by lazy {
        GridSpacingItemDecoration(
                SPAN_COUNT, resources.getDimension(R.dimen.margin_grid).toInt())
    }

    private var isLinearLayoutManager: Boolean = false

    private lateinit var adapter: FavoritesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var clothViewModel: ClothViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        clothViewModel = injectViewModel(viewModelFactory)

        binding = MyFavoriteFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        linearLayoutManager = LinearLayoutManager(activity)
        gridLayoutManager = GridLayoutManager(activity, SPAN_COUNT)
        setLayoutManager()

        adapter = FavoritesAdapter(clothViewModel.gerFavaoriteCombinationList())
        binding.rvMyFavorites.adapter = adapter


        return binding.root

    }

    private fun setLayoutManager() {
        val recyclerView = binding.rvMyFavorites

        var scrollPosition = 0
        // If a layout manager has already been set, get current scroll position.
        if (recyclerView.layoutManager != null) {
            scrollPosition = (recyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()
        }

        if (isLinearLayoutManager) {
            recyclerView.removeItemDecoration(gridDecoration)
            recyclerView.addItemDecoration(linearDecoration)
            recyclerView.layoutManager = linearLayoutManager
        } else {
            recyclerView.removeItemDecoration(linearDecoration)
            recyclerView.addItemDecoration(gridDecoration)
            recyclerView.layoutManager = gridLayoutManager
        }

        recyclerView.scrollToPosition(scrollPosition)
    }

    companion object {
        const val SPAN_COUNT = 2
    }


}