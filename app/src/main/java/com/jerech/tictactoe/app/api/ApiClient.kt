package com.jerech.tictactoe.app.api;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.jerech.tictactoe.app.BuildConfig
import com.jerech.tictactoe.app.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by jeremias on 05/10/19.
 */

class ApiClient {


    fun getClient(context: Context?) : Retrofit {

        val client = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

        var retrofit = Retrofit.Builder()
            .baseUrl(context?.resources?.getString(R.string.base_url_api) +
                    context?.resources?.getString(R.string.endpoint))
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }


}
