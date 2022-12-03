package com.example.homework.data

import android.content.res.Resources
import com.example.homework.R

fun shopList(resources: Resources): List<Shop>{
    return listOf(
        Shop(
            id = 1,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop1,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 2,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop2,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 3,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop3,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 4,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop4,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 5,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop5,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 6,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop6,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 7,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop7,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 8,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop7,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 9,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop7,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        ),
        Shop(
            id = 10,
            name = resources.getString(R.string.shop1_name),
            image = R.drawable.shop7,
            score = resources.getString(R.string.shop1_score).toDouble(),
            saleNumber = resources.getString(R.string.shop1_saleNumber).toInt()
        )
    )
}
