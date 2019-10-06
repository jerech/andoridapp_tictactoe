package com.jerech.tictactoe.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.jerech.tictactoe.app.api.ApiClient
import com.jerech.tictactoe.app.api.ApiInterface
import com.jerech.tictactoe.app.api.pojo.ApiError
import com.jerech.tictactoe.app.api.pojo.PlayResponse
import com.jerech.tictactoe.app.model.Game
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var game: Game ?= null
    private var myCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCompositeDisposable = CompositeDisposable()

        btnGame.setOnClickListener(View.OnClickListener {
            startGame()
        })

        position1.setOnClickListener(View.OnClickListener {
            play(0)
        })
        position2.setOnClickListener(View.OnClickListener {
            play(1)
        })
        position3.setOnClickListener(View.OnClickListener {
            play(2)
        })
        position4.setOnClickListener(View.OnClickListener {
            play(3)
        })
        position5.setOnClickListener(View.OnClickListener {
            play(4)
        })
        position6.setOnClickListener(View.OnClickListener {
            play(5)
        })
        position7.setOnClickListener(View.OnClickListener {
            play(6)
        })
        position8.setOnClickListener(View.OnClickListener {
            play(7)
        })
        position9.setOnClickListener(View.OnClickListener {
            play(8)
        })

        if(game == null) {
            loBoard.visibility = View.INVISIBLE
        }

    }

    fun startGame (){
        if (etUserName.text.toString().isBlank()) {
            etUserName.setError("Debes escribir tu nombre de usuario")
            return
        }

        if(game != null) {
            game!!.board = Array<String>(9, {""} )
            generatedBoard(game!!)
        }



        var apiClient = ApiClient()
        var retrofit = apiClient.getClient(applicationContext)
        val service = retrofit.create(ApiInterface::class.java)

        progressBar.visibility = View.VISIBLE


        myCompositeDisposable?.add(service.startGame(etUserName.text.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result -> handleStartResponse(result) },
                { error -> handleError(error) }
            ))


    }

    private fun handleStartResponse(game: Game) {

        this.game  = game

        loBoard.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    fun play (position: Int){

        if(game!!.winner != null) {
            return
        }

        tvMessage.text = ""

        game!!.board!![position]= "X"
        generatedBoard(game!!)

        var apiClient = ApiClient()
        var retrofit = apiClient.getClient(applicationContext)
        val service = retrofit.create(ApiInterface::class.java)

        progressBar.visibility = View.VISIBLE

        myCompositeDisposable?.add(service.play(game!!.id, position)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { result -> handlePlayResponse(result) },
                { error -> handleError(error) }
            ))

    }

    private fun handleError(error: Throwable) {
        Log.i("TAG", error.message)
        try {
            val apiError = Gson().fromJson(error.message, ApiError::class.java)
            tvMessage.text = apiError.message
        } catch (e: Exception) {
            e.printStackTrace()
        }
        progressBar.visibility = View.GONE
    }

    private fun handlePlayResponse(play: PlayResponse) {

        this.game  = play.currentGame
        //Toast.makeText(applicationContext, play.message, Toast.LENGTH_LONG).show()
        tvMessage.text = play.message

        generatedBoard(game!!)
        progressBar.visibility = View.GONE

    }

    override fun onDestroy() {
        super.onDestroy()

        myCompositeDisposable?.clear()

    }

    fun generatedBoard(game: Game) {

        position1.text = if (!game.board?.get(0).equals("-")) game.board?.get(0) else ""
        position2.text = if (!game.board?.get(1).equals("-")) game.board?.get(1) else ""
        position3.text = if (!game.board?.get(2).equals("-")) game.board?.get(2) else ""
        position4.text = if (!game.board?.get(3).equals("-")) game.board?.get(3) else ""
        position5.text = if (!game.board?.get(4).equals("-")) game.board?.get(4) else ""
        position6.text = if (!game.board?.get(5).equals("-")) game.board?.get(5) else ""
        position7.text = if (!game.board?.get(6).equals("-")) game.board?.get(6) else ""
        position8.text = if (!game.board?.get(7).equals("-")) game.board?.get(7) else ""
        position9.text = if (!game.board?.get(8).equals("-")) game.board?.get(8) else ""

    }

}
