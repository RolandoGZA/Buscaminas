package com.example.pachexplode.buscaminas

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class MinesweeperViewModel : ViewModel() {
    // Configuración inicial (puedes cambiar esto luego para niveles de dificultad)
    private val rows = 10
    private val cols = 10
    private val mines = 15

    private var engine = MinesweeperEngine(rows, cols, mines)

    // Estado del tablero que la UI observará
    private val _boardState = mutableStateOf(engine.board)
    val boardState: State<Array<Array<Cell>>> = _boardState

    // Estados adicionales del juego
    var isGameOver = mutableStateOf(false)
        private set
    var isGameWon = mutableStateOf(false)
        private set

    // Acción: Tocar una celda
    fun onCellClick(row: Int, col: Int) {
        if (isGameOver.value || isGameWon.value) return

        engine.revealCell(row, col)

        // Verificamos derrota
        if (engine.board[row][col].isMine) {
            isGameOver.value = true
            revealAllMines()
        } else if (engine.isGameWon()) {
            isGameWon.value = true
        }

        // Forzamos la actualización del estado de la UI
        triggerRecomposition()
    }

    // Acción: Clic largo (poner/quitar bandera)
    fun onCellLongClick(row: Int, col: Int) {
        if (isGameOver.value || isGameWon.value) return

        engine.toggleFlag(row, col)

        // Esta es la llamada mágica que refresca la pantalla
        triggerRecomposition()
    }

    // Reiniciar el juego
    fun resetGame() {
        engine = MinesweeperEngine(rows, cols, mines)
        _boardState.value = engine.board
        isGameOver.value = false
        isGameWon.value = false
    }

    private fun revealAllMines() {
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (engine.board[r][c].isMine) {
                    engine.board[r][c].isRevealed = true
                }
            }
        }
    }

    private fun triggerRecomposition() {
        // .map { it.copyOf() }.toTypedArray() asegura una copia profunda de las filas
        _boardState.value = engine.board.map { it.copyOf() }.toTypedArray()
    }
}