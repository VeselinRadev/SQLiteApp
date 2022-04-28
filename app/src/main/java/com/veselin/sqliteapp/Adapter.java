package com.veselin.sqliteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.RecyclerViewHolder>{
    private List<UserModel> users;
    private Context context;

    // RecyclerView recyclerView;
    public Adapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final UserModel mUser = users.get(position);
        holder.getName().setText("User Name: " + mUser.getUsername() + System.lineSeparator() + "Password: " + mUser.getPass());
    }


    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
        }

        public TextView getName(){
            return name;
        }

    }
}
