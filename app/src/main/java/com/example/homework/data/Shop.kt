package com.example.homework.data

import androidx.annotation.DrawableRes

data class Shop (
    val id: Long,
    val name:String,
    val score: Double,
    val saleNumber: Int,
    @DrawableRes
    val image: Int?
)