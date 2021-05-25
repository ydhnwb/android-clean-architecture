package com.ydhnwb.cleanarchitectureexercise.presentation.common.extension

import android.util.Patterns

fun String.isEmail() : Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}