package com.example.myproject.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.api.ApiRx;
import com.example.myproject.databinding.FragmentRegisterBinding;
import com.example.myproject.databinding.FragmentSingleUserBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SingleUserFragment extends Fragment {

    private FragmentSingleUserBinding binding;
    private int id;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "SingleUserFragment";

    public SingleUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = SingleUserFragmentArgs.fromBundle(getArguments()).getId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingleUserBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(binding.getRoot()).popBackStack());
    }

    @Override
    public void onStart() {
        super.onStart();
        getSingleUser();
    }

    private void getSingleUser() {
        Disposable disposable = ApiRx.getSingleUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(singleUserResponse -> {
                            binding.toolbar.setTitle(singleUserResponse.getData().getFirst_name() + " " + singleUserResponse.getData().getLast_name());
                            binding.tvFirstName.setText(singleUserResponse.getData().getFirst_name());
                            binding.tvLastName.setText(singleUserResponse.getData().getLast_name());
                            binding.tvEmail.setText(singleUserResponse.getData().getEmail());
                            binding.tvCompany.setText(singleUserResponse.getAd().getCompany());
                            binding.tvUrl.setText(singleUserResponse.getAd().getUrl());
                            binding.tvDescription.setText(singleUserResponse.getAd().getText());
                            Glide.with(requireActivity()).load(singleUserResponse.getData().getAvatar()).into(binding.circularImageView2);
                        },
                        error -> Log.e(TAG, "getSingleUser: ", error));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}