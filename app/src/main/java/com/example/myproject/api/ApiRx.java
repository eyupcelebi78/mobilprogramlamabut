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

public class ApiRx {

    public static Single<RegisterResponse> register(RegisterRequest registerRequest) {
        return Api.getInstance().register(registerRequest);
    }

    public static Single<LoginResponse> login(LoginRequest loginRequest) {
        return Api.getInstance().login(loginRequest);
    }

    public static Single<CreateResponse> create(CreateRequest createRequest) {
        return Api.getInstance().create(createRequest);
    }

    public static Single<UsersResponse> getUsers(int pageNumber) {
        return Api.getInstance().getUsers(pageNumber);
    }

    public static Single<SingleUserResponse> getSingleUser(int id) {
        return Api.getInstance().getSingleUser(id);
    }


}
