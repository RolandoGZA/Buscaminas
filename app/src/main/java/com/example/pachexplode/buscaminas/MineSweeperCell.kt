package com.example.pachexplode.buscaminas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MinesweeperCell(
    cell: Cell,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current // Para la vibración
    // Definimos el color de fondo según el estado
    val backgroundColor = when {
        cell.isRevealed -> if (cell.isMine) Color.Red else Color.LightGray
        else -> Color.DarkGray
    }

    Box(
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (cell.isRevealed) {
            if (cell.isMine) {
                Text(text = "💣", fontSize = 18.sp)
            } else if (cell.adjacentMines > 0) {
                Text(
                    text = cell.adjacentMines.toString(),
                    color = getNumberColor(cell.adjacentMines),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        } else if (cell.isFlagged) {
            Text(text = "🚩", fontSize = 18.sp)
        }
    }
}

// Función auxiliar para los colores clásicos de los números
fun getNumberColor(number: Int): Color {
    return when (number) {
        1 -> Color.Blue
        2 -> Color(0xFF388E3C) // Verde
        3 -> Color.Red
        4 -> Color(0xFF303F9F) // Azul oscuro
        5 -> Color(0xFFD32F2F) // Rojo oscuro
        else -> Color.Black
    }
}