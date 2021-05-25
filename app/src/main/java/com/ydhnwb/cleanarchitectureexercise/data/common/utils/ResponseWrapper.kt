package com.ydhnwb.cleanarchitectureexercise.data.common.utils

import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    var code: Int,
    @SerializedName("message") var message : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("errors") var errors : List<String>? = null,
    @SerializedName("data") var data : List<T>? = null
)


data class WrappedResponse<T> (
    var code: Int,
    @SerializedName("message") var message : String,
    @SerializedName("status") var status : Boolean,
    @SerializedName("errors") var errors : List<String>? = null,
    @SerializedName("data") var data : T? = null
)