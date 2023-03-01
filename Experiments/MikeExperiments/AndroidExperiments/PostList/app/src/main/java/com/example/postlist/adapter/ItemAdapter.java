package com.example.affirmations.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.postlist.R;
import com.example.postlist.model.Posts;


import java.util.List;

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private final List<Posts> dataset;

    public ItemAdapter(Context context, List<Posts> dataset) {
        this.context = context;
        this.dataset = dataset;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.item_title);
        }
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View adapterLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ItemViewHolder(adapterLayout);
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Posts item = dataset.get(position);
        holder.textView.setText(context.getResources().getString(item.getStringResourceId()));
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

