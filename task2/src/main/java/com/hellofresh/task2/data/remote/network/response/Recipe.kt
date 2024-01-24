package com.hellofresh.task2.data.remote.network.response

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("calories")
    var calories: String?,
    @SerializedName("carbos")
    var carbos: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("difficulty")
    var difficulty: Int?,
    @SerializedName("fats")
    var fats: String?,
    @SerializedName("headline")
    var headline: String?,
    @SerializedName("id")
    var id: String,
    @SerializedName("image")
    var image: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("proteins")
    var proteins: String?,
    @SerializedName("thumb")
    var thumb: String?,
    @SerializedName("time")
    var time: String?
)