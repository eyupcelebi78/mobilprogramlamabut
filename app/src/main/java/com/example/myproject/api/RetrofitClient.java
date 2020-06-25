package com.example.myproject.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://reqres.in/";

    private RetrofitClient() {
    }

    private static OkHttpClient instance;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                instance = createClient();
            }
        }
        return instance;
    }

    private static OkHttpClient createClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .followRedirects(false)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
    }

    public static <T> T buildApi(String url, Class<T> apiClass) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getInstance())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(apiClass);
    }

}
