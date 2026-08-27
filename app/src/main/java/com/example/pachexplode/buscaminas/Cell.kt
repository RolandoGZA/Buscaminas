package com.example.pachexplode.buscaminas

data class Cell(
    val row: Int,
    val col: Int,
    var isMine: Boolean = false,
    var isRevealed: Boolean = false, // Cambiado de var a val
    var isFlagged: Boolean = false,  // Cambiado de var a val
    var adjacentMines: Int = 0
)
