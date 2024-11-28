package com.example.tic_tac_toe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : ComponentActivity() {

    private lateinit var game: GameController
    private lateinit var statusTextView: TextView
    private lateinit var buttons: Array<Array<Button>>

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        statusTextView = findViewById(R.id.statusTextView)
        val playAgainButton: Button = findViewById(R.id.playAgainButton)

        game = GameController()

        buttons = Array(3) { Array(3) { Button(this) } }

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = resources.getIdentifier("button_${i}_${j}",
                    "id", packageName)
                buttons[i][j] = findViewById(buttonId)
                buttons[i][j].setOnClickListener { onCellClicked(i, j) }
            }
        }

        playAgainButton.setOnClickListener {
            game.resetGame()
            updateUI()
        }

        updateUI()
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