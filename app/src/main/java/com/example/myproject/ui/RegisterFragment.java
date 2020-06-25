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
import com.example.myproject.databinding.FragmentLoginBinding;
import com.example.myproject.databinding.FragmentRegisterBinding;
import com.example.myproject.model.LoginRequest;
import com.example.myproject.model.RegisterRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "RegisterFragment";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding.btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();

        if (email.length() == 0 || password.length() == 0) {
            Toast.makeText(requireActivity(), R.string.enter_info, Toast.LENGTH_SHORT).show();
        } else {
            Disposable disposable = ApiRx.register(new RegisterRequest(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponse -> {
                                Log.d(TAG, "register: completed");
                                Toast.makeText(requireActivity(), getActivity().getResources().getString(R.string.user_created), Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.usersFragment);
                            },
                            error -> {
                                Log.e(TAG, "register: ", error);
                                Toast.makeText(requireActivity(), getActivity().getResources().getString(R.string.use_example_user), Toast.LENGTH_LONG).show();

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