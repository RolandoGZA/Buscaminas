package com.example.pachexplode.buscaminas

class MinesweeperEngine(val rows: Int, val cols: Int, val mineCount: Int) {
    val board: Array<Array<Cell>> = Array(rows) { r ->
        Array(cols) { c -> Cell(r, c) }
    }

    init {
        placeMines()
        calculateNumbers()
    }

    private fun placeMines() {
        var placed = 0
        while (placed < mineCount) {
            val r = (0 until rows).random()
            val c = (0 until cols).random()
            if (!board[r][c].isMine) {
                board[r][c].isMine = true
                placed++
            }
        }
    }

    private fun calculateNumbers() {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (!board[r][c].isMine) {
                    board[r][c].adjacentMines = countAdjacentMines(r, c)
                }
            }
        }
    }

    private fun countAdjacentMines(r: Int, c: Int): Int {
        var count = 0
        for (i in -1..1) {
            for (j in -1..1) {
                val nr = r + i
                val nc = c + j
                if (nr in 0 until rows && nc in 0 until cols && board[nr][nc].isMine) {
                    count++
                }
            }
        }
        return count
    }

    fun revealCell(r: Int, c: Int) {
        val cell = board[r][c]
        if (cell.isRevealed || cell.isFlagged) return

        // Reemplazamos la celda vieja por una nueva copia con isRevealed = true
        board[r][c] = cell.copy(isRevealed = true)

        if (board[r][c].isMine) return

        if (board[r][c].adjacentMines == 0) {
            for (i in -1..1) {
                for (j in -1..1) {
                    val nr = r + i
                    val nc = c + j
                    if (nr in 0 until rows && nc in 0 until cols) {
                        revealCell(nr, nc)
                    }
                }
            }
        }
    }

    fun toggleFlag(r: Int, c: Int) {
        val cell = board[r][c]
        // Solo podemos poner bandera si la casilla NO está revelada
        if (!cell.isRevealed) {
            // IMPORTANTE: Usar .copy() para crear una nueva referencia
            board[r][c] = cell.copy(isFlagged = !cell.isFlagged)
        }
    }

    fun isGameWon(): Boolean {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                // Si hay una celda que NO es mina y NO ha sido revelada, aún no ganas
                if (!board[r][c].isMine && !board[r][c].isRevealed) {
                    return false
                }
            }
        }
        return true
    }


}