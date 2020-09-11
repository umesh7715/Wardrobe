package com.andromesh.wardrobe.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.andromesh.my_portfolio.di.Injectable
import com.andromesh.my_portfolio.di.injectViewModel
import com.andromesh.wardrobe.R
import com.andromesh.wardrobe.adapter.ClothsPagerAdapter
import com.andromesh.wardrobe.data.model.Cloth
import com.andromesh.wardrobe.data.model.FavaoriteCombination
import com.andromesh.wardrobe.databinding.WardrobeFragmentBinding
import com.andromesh.wardrobe.ui.viewmodel.ClothViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import javax.inject.Inject


class WardeobeFragment : Fragment(), Injectable {

    private lateinit var clothsPagerAdapter: ClothsPagerAdapter
    private lateinit var binding: WardrobeFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var clothViewModel: ClothViewModel


    private lateinit var clothType: String

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        clothViewModel = injectViewModel(viewModelFactory)


        binding = WardrobeFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root



        binding.fabTakeShirtPhoto.setOnClickListener {

            ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()

            clothType = Cloth.Companion.ClothTypes.shirt.name
        }

        binding.fabTakeTrouserPhoto.setOnClickListener {

            ImagePicker.with(this)
                    .compress(1024)         //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                    .start()

            clothType = Cloth.Companion.ClothTypes.trouser.name

        }

        binding.bShuffle.setOnClickListener {

            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
            binding.bShuffle.startAnimation(animation)

            var shirtCounts: Int = (binding.vpShirts.adapter as ClothsPagerAdapter).count
            var trouserCounts: Int = (binding.vpTrousers.adapter as ClothsPagerAdapter).count


            binding.vpShirts.currentItem = (1..shirtCounts).shuffled().first()
            binding.vpTrousers.currentItem = (1..trouserCounts).shuffled().first()

            var favaoriteCombination: FavaoriteCombination? = clothViewModel.gerFavaoriteCombination(getClothByPosition(Cloth.Companion.ClothTypes.shirt.name, binding.vpShirts.currentItem)
                    + "<||>" + getClothByPosition(Cloth.Companion.ClothTypes.trouser.name, binding.vpTrousers.currentItem))

            if (favaoriteCombination != null) {
                binding.bFavorite.setBackgroundResource(R.drawable.favorite_filled)
            } else {
                binding.bFavorite.setBackgroundResource(R.drawable.favorite)
            }

        }

        binding.bFavorite.setOnClickListener {

            binding.bFavorite.setBackgroundResource(R.drawable.favorite_filled)
            val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.popup)
            binding.bFavorite.startAnimation(animation)

            clothViewModel.insertFavaoriteCombination(
                    FavaoriteCombination(getClothByPosition(Cloth.Companion.ClothTypes.shirt.name, binding.vpShirts.currentItem)
                            + "<||>" + getClothByPosition(Cloth.Companion.ClothTypes.trouser.name, binding.vpTrousers.currentItem),
                            true))

        }

        binding.bViewFavairoites.setOnClickListener {
            val action = WardeobeFragmentDirections.actionWardeobeFragmentToMyFavourites()
            findNavController().navigate(action)
        }


        binding.vpTrousers.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                // Check if this is the page you want.

                var favaoriteCombination: FavaoriteCombination? = clothViewModel.gerFavaoriteCombination(getClothByPosition(Cloth.Companion.ClothTypes.shirt.name, binding.vpShirts.currentItem)
                        + "<||>" + getClothByPosition(Cloth.Companion.ClothTypes.trouser.name, binding.vpTrousers.currentItem))

                if (favaoriteCombination != null) {
                    binding.bFavorite.setBackgroundResource(R.drawable.favorite_filled)
                } else {
                    binding.bFavorite.setBackgroundResource(R.drawable.favorite)

                }
            }
        })

        binding.vpShirts.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                // Check if this is the page you want.

                var favaoriteCombination: FavaoriteCombination? = clothViewModel.gerFavaoriteCombination(getClothByPosition(Cloth.Companion.ClothTypes.shirt.name, binding.vpShirts.currentItem)
                        + "<||>" + getClothByPosition(Cloth.Companion.ClothTypes.trouser.name, binding.vpTrousers.currentItem))

                if (favaoriteCombination != null) {
                    binding.bFavorite.setBackgroundResource(R.drawable.favorite_filled)
                } else {
                    binding.bFavorite.setBackgroundResource(R.drawable.favorite)
                }
            }
        })

        setupShirtViewPager()

        setupTrouserViewPager()

        return binding.root

    }

    fun getClothByPosition(clothType: String, position: Int): String {

        if (clothType == Cloth.Companion.ClothTypes.shirt.name) {
            return binding.vpShirts.adapter?.getPageTitle(position).toString()
        } else {
            return binding.vpTrousers.adapter?.getPageTitle(position).toString()
        }
    }

    fun setupShirtViewPager() {

        clothsPagerAdapter = fragmentManager?.let { ClothsPagerAdapter(it) }!!

        for (cloth: Cloth in clothViewModel.gerClothes(Cloth.Companion.ClothTypes.shirt.name)) {
            clothsPagerAdapter.addFragment(PagerClothFragment.newInstance(cloth.filePath), cloth.filePath)
        }

        binding.vpShirts.adapter = clothsPagerAdapter
    }

    fun setupTrouserViewPager() {

        clothsPagerAdapter = fragmentManager?.let { ClothsPagerAdapter(it) }!!

        for (cloth: Cloth in clothViewModel.gerClothes(Cloth.Companion.ClothTypes.trouser.name)) {
            clothsPagerAdapter.addFragment(PagerClothFragment.newInstance(cloth.filePath), cloth.filePath)
        }

        binding.vpTrousers.adapter = clothsPagerAdapter
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (resultCode) {
            Activity.RESULT_OK -> {

                //You can also get File Path from intent
                val filePath: String? = ImagePicker.getFilePath(data)

                val cloth = filePath?.let { Cloth(it, clothType) }
                cloth?.let { clothViewModel.insertCloth(it) }

                if (clothType == Cloth.Companion.ClothTypes.shirt.name) {
                    (binding.vpShirts.adapter as ClothsPagerAdapter).addFragment(PagerClothFragment.newInstance(filePath.toString()), filePath.toString())
                    binding.vpShirts.adapter?.notifyDataSetChanged()
                    binding.vpShirts.currentItem = (binding.vpTrousers.adapter as ClothsPagerAdapter).count


                } else {
                    (binding.vpTrousers.adapter as ClothsPagerAdapter).addFragment(PagerClothFragment.newInstance(filePath.toString()), filePath.toString())
                    binding.vpTrousers.adapter?.notifyDataSetChanged()
                    binding.vpTrousers.currentItem = (binding.vpTrousers.adapter as ClothsPagerAdapter).count
                }

            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}