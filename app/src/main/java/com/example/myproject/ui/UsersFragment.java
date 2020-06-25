package com.example.myproject.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.api.ApiRx;
import com.example.myproject.databinding.FragmentSingleUserBinding;
import com.example.myproject.databinding.FragmentUsersBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;


public class UsersFragment extends Fragment implements UserListener {

    private FragmentUsersBinding binding;
    private CompositeDisposable compositeDisposable;
    private UsersAdapter adapter;
    private static final String TAG = "UsersFragment";

    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUsersBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        binding.toolbar.setOnMenuItemClickListener(item -> {
            onNavDestinationSelected(item, Navigation.findNavController(binding.getRoot()));
            return true;
        });

        adapter = new UsersAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        getUsers(1);
        getUsers(2);
    }

    private void getUsers(int page) {
        Disposable disposable = ApiRx.getUsers(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(usersResponse -> adapter.setUserList(usersResponse.getData()),
                        error -> Log.e(TAG, "getUsers: ", error));
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }

    @Override
    public void onClick(int id) {
        UsersFragmentDirections.ActionUsersFragmentToSingleUserFragment action = UsersFragmentDirections.actionUsersFragmentToSingleUserFragment();
        action.setId(id);
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}