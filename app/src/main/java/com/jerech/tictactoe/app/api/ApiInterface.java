package com.jerech.tictactoe.app.api;

import java.util.List;

import com.jerech.tictactoe.app.api.pojo.PlayResponse;
import com.jerech.tictactoe.app.model.Game;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by jeremias on 05/10/19.
 */

public interface ApiInterface {



    @FormUrlEncoded
    @POST("games/play")
    Call<PlayResponse> play(@Field("idGame") String idGame,
                            @Field("position") int position);
    
    @FormUrlEncoded
    @POST("games/start")
    Call<Game> startGame(@Field("userName") String userName);

    @GET("games")
    Call<List<Game>> getGames(@Query("userName") String userName);


}
