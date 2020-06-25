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
import com.example.myproject.model.CreateRequest;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CreateUserFragment extends Fragment {

    private FragmentCreateUserBinding binding;
    private CompositeDisposable compositeDisposable;
    private static final String TAG = "CreateUserFragment";

    public CreateUserFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding.toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(binding.getRoot()).popBackStack());
        binding.btnCreateUser.setOnClickListener(v -> createUser());
    }

    private void createUser() {
        String username = binding.etUsername.getText().toString();
        String job = binding.etJob.getText().toString();

        if (username.length() == 0 || job.length() == 0) {
            Toast.makeText(requireActivity(), getActivity().getResources().getString(R.string.enter_info), Toast.LENGTH_SHORT).show();
        }
        Disposable disposable = ApiRx.create(new CreateRequest(username, job))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createResponse -> {
                            Toast.makeText(requireActivity(), getActivity().getResources().getString(R.string.user_created), Toast.LENGTH_LONG).show();
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.usersFragment);
                        },
                        error -> Log.e(TAG, "createUser: ", error));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}