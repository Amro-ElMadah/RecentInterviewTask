package com.hellofresh.task1.data.model

import java.util.Date

data class Subscription(
    val id: Int,
    val deliveryDay: Date,
    val isForFamily: Boolean,
)
