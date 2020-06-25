package com.example.myproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.User;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private List<User> userList = new ArrayList<>();
    private View view;
    private UserListener listener;

    public UsersAdapter(UserListener listener) {
        this.listener = listener;
    }

    public void setUserList(List<User> userList) {
        this.userList.addAll(userList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = userList.get(position);
        holder.firstName.setText(user.getFirst_name());
        holder.lastName.setText(user.getLast_name());
        holder.email.setText(user.getEmail());
        Glide.with(view).load(user.getAvatar()).into(holder.image);
        holder.userItem.setOnClickListener(v -> listener.onClick(user.getId()));

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout userItem;
        private CircularImageView image;
        private TextView firstName;
        private TextView lastName;
        private TextView email;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circularImageView);
            firstName = itemView.findViewById(R.id.tv_first_name);
            lastName = itemView.findViewById(R.id.tv_last_name);
            email = itemView.findViewById(R.id.tv_email);
            userItem = itemView.findViewById(R.id.user_item);
        }
    }
}
