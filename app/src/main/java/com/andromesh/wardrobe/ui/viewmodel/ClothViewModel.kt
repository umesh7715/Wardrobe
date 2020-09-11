package com.andromesh.wardrobe.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.andromesh.my_portfolio.di.CoroutineScropeIO
import com.andromesh.wardrobe.data.model.Cloth
import com.andromesh.wardrobe.data.model.FavaoriteCombination
import com.andromesh.wardrobe.ui.repositories.ClothRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

class ClothViewModel @Inject constructor(private val clothRepository: ClothRepository,
                                         @CoroutineScropeIO private val ioCoroutineScope: CoroutineScope)
    : ViewModel() {

    fun insertCloth(cloth: Cloth) {
        clothRepository.insertCloth(cloth, ioCoroutineScope)
    }

    fun gerClothes(clothType: String): List<Cloth> {
        return clothRepository.gerClothes(clothType, ioCoroutineScope)
    }

    fun insertFavaoriteCombination(favaoriteCombination: FavaoriteCombination) {
        clothRepository.insertFavaoriteCombination(favaoriteCombination, ioCoroutineScope)
    }

    fun gerFavaoriteCombinationList(): List<FavaoriteCombination> {
        return clothRepository.getFavaoriteCombinationList(ioCoroutineScope)
    }

    fun gerFavaoriteCombination(combination: String): FavaoriteCombination {
        return clothRepository.getFavaoriteCombination(combination, ioCoroutineScope)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

}