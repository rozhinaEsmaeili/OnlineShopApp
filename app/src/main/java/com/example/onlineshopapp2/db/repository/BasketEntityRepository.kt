package com.example.onlineshopapp2.db.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.onlineshopapp2.db.OnlineShopDataBase
import com.example.onlineshopapp2.db.dao.BasketEntityDao
import com.example.onlineshopapp2.db.models.BasketEntity

class BasketEntityRepository(application: Application) {

    private var basketDao: BasketEntityDao
    private var basketListLive: LiveData<List<BasketEntity>>


    init {
        var db = OnlineShopDataBase.getInstance(application)
        basketDao = db.basketDao()
        basketListLive = basketDao.getAllLive()
    }

    fun getBasketList(): List<BasketEntity> {
        return basketDao.getAll()
    }

    suspend fun insert(basket: BasketEntity) {
        basketDao.add(basket)
    }

    suspend fun update(basket: BasketEntity) {
        basketDao.update(basket)
    }

    suspend fun delete(basket: BasketEntity) {
        basketDao.delete(basket)
    }

    suspend fun deleteAll() {
        basketDao.deleteAll()
    }

    fun getBasketListLive(): LiveData<List<BasketEntity>> {
        return basketListLive
    }

    suspend fun incrementQuantity(basket: BasketEntity) {
        basket.quantity++
        update(basket)
    }

    suspend fun decrementQuantity(basket: BasketEntity) {
        basket.quantity--
        if (basket.quantity <= 0) delete(basket)
        else update(basket)
    }

}