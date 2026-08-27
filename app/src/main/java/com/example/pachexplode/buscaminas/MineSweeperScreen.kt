package com.example.pachexplode.buscaminas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MinesweeperScreen(viewModel: MinesweeperViewModel) {
    val board = viewModel.boardState.value
    val isGameOver = viewModel.isGameOver.value
    val isGameWon = viewModel.isGameWon.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título o Estado del Juego
        Text(
            text = when {
                isGameOver -> "¡BOOM! Perdiste 💥"
                isGameWon -> "¡ERES UN CRACK! 🏆"
                else -> "Buscaminas Kotlin"
            },
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // El Tablero
        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(10), // Coincide con las columnas del Engine
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp)
            ) {
                // Aplanamos la matriz para que el Grid la lea
                items(board.size * board[0].size) { index ->
                    val row = index / 10
                    val col = index % 10
                    val cell = board[row][col]

                    MinesweeperCell(
                        cell = cell,
                        onClick = { viewModel.onCellClick(row, col) },
                        onLongClick = { viewModel.onCellLongClick(row, col) }
                    )
                }
            }
        }

        // Botón de Reinicio
        Button(
            onClick = { viewModel.resetGame() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Reiniciar Juego")
        }
    }
}