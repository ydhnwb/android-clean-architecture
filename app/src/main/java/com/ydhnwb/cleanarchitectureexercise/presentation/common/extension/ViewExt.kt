package com.ydhnwb.cleanarchitectureexercise.presentation.common.extension

import android.view.View

fun View.gone(){
    visibility = View.GONE
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}