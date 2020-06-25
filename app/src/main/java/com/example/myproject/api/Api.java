package com.example.myproject.api;

import com.example.myproject.model.CreateRequest;
import com.example.myproject.model.CreateResponse;
import com.example.myproject.model.LoginRequest;
import com.example.myproject.model.LoginResponse;
import com.example.myproject.model.RegisterRequest;
import com.example.myproject.model.RegisterResponse;
import com.example.myproject.model.SingleUserResponse;
import com.example.myproject.model.UsersResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.myproject.api.RetrofitClient.getInstance;

public interface Api {

    @POST("register")
    Single<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Single<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("users")
    Single<CreateResponse> create(@Body CreateRequest createRequest);

    @GET("users")
    Single<UsersResponse> getUsers(@Query("page") int page);

    @GET("users/{id}")
    Single<SingleUserResponse> getSingleUser(@Path("id") int id);

    static Api getInstance() {
        return RetrofitClient.buildApi(RetrofitClient.BASE_URL + "api/", Api.class);
    }
}
