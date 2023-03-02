package com.example.servington_from_ground_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class userPostRecyclerAdapter extends RecyclerView.Adapter<userPostRecyclerAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<Posts> postArrayList;
    private Context context;

    // creating a constructor for our variables.
    public userPostRecyclerAdapter(ArrayList<Posts> postArrayList, Context context) {
        this.postArrayList = postArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userpostlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        Posts modal = postArrayList.get(position);
        holder.name.setText(modal.getPostTitle());
        holder.date.setText(modal.getPostDate());

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return postArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView name, date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids.
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);

        }
    }
}
