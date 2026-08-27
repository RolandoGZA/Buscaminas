# PachExplode

**PachExplode** es un juego de Buscaminas (Minesweeper) desarrollado para Android utilizando **Jetpack Compose**.

## Características

- Tablero de 10x10 con 15 minas (configurable).
- Mecánica clásica de juego:
    - Clic simple para revelar celdas.
    - Clic largo para colocar o quitar banderas.
- Detección de victoria y derrota.
- Reinicio del juego.
- Interfaz moderna construida íntegramente con Jetpack Compose.
- Arquitectura basada en **MVVM** (Model-View-ViewModel).

## Tecnologías Utilizadas

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Arquitectura:** MVVM con LiveData/State.
- **Mínimo SDK:** 24 (Android 7.0)
- **Target SDK:** 36

## Estructura del Proyecto

El código fuente principal se encuentra en `app/src/main/java/com/example/pachexplode/buscaminas/`:
- `MinesweeperEngine.kt`: Lógica central del juego (generación de minas, cálculo de números, algoritmos de revelado).
- `MinesweeperViewModel.kt`: Manejo del estado del juego y comunicación con la UI.
- `MinesweeperScreen.kt`: Componibles que definen la interfaz de usuario.
- `Cell.kt`: Modelo de datos para cada celda del tablero.

## Cómo ejecutar el proyecto

1. Clona el repositorio.
2. Abre el proyecto en **Android Studio (Ladybug o superior)**.
3. Asegúrate de tener instalado el SDK de Android (API 36).
4. Sincroniza el proyecto con Gradle.
5. Ejecuta la aplicación en un emulador o dispositivo físico.

## Capturas de Pantalla (Próximamente)

---
*Desarrollado con ❤️ usando Kotlin y Jetpack Compose.*
