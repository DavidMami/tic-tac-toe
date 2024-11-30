package com.example.tic_tac_toe

class GameController {

    enum class Player { X, O }

    private val board: Array<Array<Player?>> = Array(3) { arrayOfNulls<Player>(3) }
    var currentPlayer: Player = Player.X

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
            return true
        }
        return false
    }

    fun getCell(row: Int, col: Int): Player? {
        return board[row][col]
    }

    fun checkWinner(): Player? {
        // Check rows and columns
        for (i in 0..2) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0]
            }
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i]
            }
        }

        // Check diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]
        }

        // Check for draw
        if (board.all { row -> row.all { it != null } }) {
            return null
        }

        return null
    }

    fun isTie(): Boolean {
        return board.all { row -> row.all { it != null } } && checkWinner() == null
    }


    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = null
            }
        }
        currentPlayer = Player.X
    }
}
