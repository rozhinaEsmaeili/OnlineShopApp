package com.example.onlineshopapp2.db.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.util.query
import com.example.onlineshopapp2.db.models.BasketEntity
import com.example.onlineshopapp2.db.repository.BasketEntityRepository

class BasketEntityViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = BasketEntityRepository(application)
    var dataListLive = mutableStateOf<List<BasketEntity>>(listOf())

    private suspend fun insert(basket: BasketEntity) {
        repository.insert(basket)
    }

    suspend fun addToBasket(basket: BasketEntity) {

        val existing = dataListLive.value.firstOrNull { it ->
            it.productId == basket.productId &&
                    it.sizeId == basket.sizeId &&
                    it.colorId == basket.colorId
        }
        if (existing != null) {
            existing.quantity++
            update(existing)
        } else {
            insert(basket)
        }
    }

    private suspend fun update(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.update(basket)
    }

    suspend fun delete(basket: BasketEntity) {
        if (basket.id <= 0) return
        repository.delete(basket)
    }

    suspend fun deleteAll() {
        repository.deleteAll()
    }

    fun getBasketList(): List<BasketEntity> {
        return repository.getBasketList()
    }

    fun getBasketListLive(): LiveData<List<BasketEntity>> {
        return repository.getBasketListLive()
    }

    suspend fun incrementQuantity(basket: BasketEntity) {
        repository.incrementQuantity(basket)
    }

    suspend fun decrementQuantity(basket: BasketEntity) {
        repository.decrementQuantity(basket)
    }
}