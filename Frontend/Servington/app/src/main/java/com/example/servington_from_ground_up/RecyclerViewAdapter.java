package com.example.servington_from_ground_up;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<PostData> list;

    public RecyclerViewAdapter(Context context, List<PostData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PostData Post = list.get(position);

        holder.textTitle.setText(Post.getTitle());
        holder.textDesc.setText(String.valueOf(Post.getDescription()));
        holder.textDate.setText(String.valueOf(Post.getDate()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textDesc, textDate;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.postcardtitle);
            textDesc = itemView.findViewById(R.id.postdesc);
            textDate = itemView.findViewById(R.id.postcarddate);
        }
    }

}