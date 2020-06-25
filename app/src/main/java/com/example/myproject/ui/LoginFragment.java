package com.example.myproject.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.api.ApiRx;
import com.example.myproject.databinding.FragmentCreateUserBinding;
import com.example.myproject.databinding.FragmentLoginBinding;
import com.example.myproject.model.LoginRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LoginFragment extends Fragment {


    private FragmentLoginBinding binding;
    private static final String TAG = "LoginFragment";
    private CompositeDisposable compositeDisposable;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding.btnLogin.setOnClickListener(v -> login());
        binding.btnRegister.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.registerFragment));
    }

    private void login() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(requireActivity(), R.string.enter_info, Toast.LENGTH_SHORT).show();
        } else {
            Disposable disposable = ApiRx.login(new LoginRequest(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponse -> {
                                Log.d(TAG, "login: completed");
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.usersFragment);
                            },
                            error -> {
                                Log.e(TAG, "login: ", error);
                                Toast.makeText(requireActivity(), getActivity().getResources().getString(R.string.user_not_found), Toast.LENGTH_SHORT).show();
                            });
            compositeDisposable.add(disposable);
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}