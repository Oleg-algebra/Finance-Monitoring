package com.example.financemonitoring.domain

data class Card(
    val id:Long = UNDEFINED_ID,
    val text: String,
    var isSelected: Boolean = false,
    val type: String = UNDEFINED_TYPE )
