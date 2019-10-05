package com.amta.socialneed.webservices;

import com.amta.socialneed.models.DatosSocialNeed;
import com.amta.socialneed.prestador.model.EntidadBancaria;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jeremias on 3/05/17.
 */

public interface ApiInterface {



    @GET("games/play")
    Call<DatosSocialNeed> getDatosEmpresa();


    @GET("entidadesBancarias")
    Call<List<EntidadBancaria>> getEntidadesBancarias(@Query("codigo_pais") String codigoPais);


}
