package com.jerech.tictactoe.app.api;

import kotlin.collections.List;

import com.jerech.tictactoe.app.api.pojo.PlayResponse;
import com.jerech.tictactoe.app.model.Game;
import io.reactivex.Observable
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by jeremias on 05/10/19.
 */

public interface ApiInterface {



    @FormUrlEncoded
    @POST("games/play")
    fun play(@Field("idGame")  idGame: String,
                            @Field("position") position: Int ): Observable<PlayResponse>

    @FormUrlEncoded
    @POST("games/start")
    fun startGame(@Field("userName") userName: String): Observable<Game>

    @GET("games")
    fun getGames(@Query("userName") userName: String): Observable<List<Game>>



}
