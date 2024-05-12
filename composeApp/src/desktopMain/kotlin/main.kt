import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        state = WindowState(
            placement = WindowPlacement.Maximized,
            position = WindowPosition.Aligned(alignment = Alignment.TopStart),
            isMinimized = false,
            size = DpSize(1920.dp, 1080.dp)
        ),
        onCloseRequest = ::exitApplication,
        title = "Курсовой проект по Моделированию",
    ) {
        App()
    }
}