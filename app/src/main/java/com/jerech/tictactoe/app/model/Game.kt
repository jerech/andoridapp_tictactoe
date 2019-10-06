package com.jerech.tictactoe.app.model;
import java.util.*

class Game constructor(id: String, createdUser: String, lastUser: String, winner: String, board: Array<String>,
                       createdDate: Date, finishDate: Date){

    var id: String = "";
    var createdUser: String = "";
    var lastUser: String = "";
    var winner: String = "";
    var board: Array<String>? = null;
    var createdDate: Date? = null;
    var finishDate: Date? = null;

    init {
        this.id = id
        this.createdUser = createdUser
        this.lastUser = lastUser
        this.winner = winner
        this.board = board
        this.createdDate = createdDate
        this.finishDate = finishDate
    }


}