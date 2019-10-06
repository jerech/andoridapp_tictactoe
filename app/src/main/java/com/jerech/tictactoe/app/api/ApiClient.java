package com.jerech.tictactoe.app.api;

import android.content.Context;
import android.content.Intent;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.jerech.tictactoe.app.R;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeremias on 05/10/19.
 */

public class ApiClient {


    public static final String CONTENT_TYPE = "Content-Type: application/json";

    private static Retrofit retrofit = null;


    private static ApiClient self = null;
    private Context context;


    private ApiClient(Context context){
        this.context = context;
    }




    public static Retrofit getClient(Context context, String token) {

        if(self==null){
            self = new ApiClient(context);
        }
        String url = context.getResources().getString(R.string.base_url_api)+context.getResources().getString(R.string.endpoint);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation() .create()))
                    .build();
        }
        return retrofit;
    }


}
