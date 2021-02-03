package com.alex.pagingsimple

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

class CheeseViewModel(app: Application): AndroidViewModel(app) {

    private val dao = CheeseDb.get(app).cheeseDao()

    val allCheeses = Pager(
        PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ){
        dao.allCheesesByName()
    }.flow

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(0, text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }

}