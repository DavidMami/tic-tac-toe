package com.example.tic_tac_toe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tic_tac_toe.ui.theme.TictactoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    private fun onCellClicked(row: Int, col: Int) {
        if (game.makeMove(row, col)) {
            updateUI()
            val winner = game.checkWinner()
            if (winner != null) {
                statusTextView.text = when (winner) {
                    GameController.Player.X -> "Player X Wins!"
                    GameController.Player.O -> "Player O Wins!"
                    else -> "It's a Draw!"
                }
                disableBoard()
            }
        }
    }

    private fun updateUI() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = when (game.getCell(i, j)) {
                    GameController.Player.X -> "X"
                    GameController.Player.O -> "O"
                    null -> ""
                }
                buttons[i][j].isEnabled = game.getCell(i, j) == null
            }
        }
        if (game.checkWinner() == null) {
            statusTextView.text =
                "Player ${if (game.currentPlayer == GameController.Player.X) "X" else "O"}'s Turn"
        }
    }

    private fun disableBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].isEnabled = false
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TictactoeTheme {
        Greeting("Android")
    }
}