package com.andromesh.wardrobe.ui.repositories

import com.andromesh.wardrobe.data.dao.ClothDao
import com.andromesh.wardrobe.data.model.Cloth
import com.andromesh.wardrobe.data.model.FavaoriteCombination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClothRepository @Inject constructor(private val clothDao: ClothDao) {

    fun insertCloth(cloth: Cloth, scope: CoroutineScope) {
        scope.launch {
            clothDao.insert(cloth)
        }

    }

    fun gerClothes(clothType: String, ioCoroutineScope: CoroutineScope): List<Cloth> = runBlocking {

        ioCoroutineScope.async {
            clothDao.getCloths(clothType)
        }.await()

    }

    fun insertFavaoriteCombination(favaoriteCombination: FavaoriteCombination, scope: CoroutineScope) {
        scope.launch {
            clothDao.insertCombination(favaoriteCombination)
        }

    }


    fun getFavaoriteCombinationList(ioCoroutineScope: CoroutineScope): List<FavaoriteCombination> = runBlocking {

        ioCoroutineScope.async {
            clothDao.getFavaoriteCombinationList()
        }.await()

    }

    fun getFavaoriteCombination(combination: String, ioCoroutineScope: CoroutineScope): FavaoriteCombination = runBlocking {

        ioCoroutineScope.async {
            clothDao.getFavaoriteCombination(combination)
        }.await()

    }

}