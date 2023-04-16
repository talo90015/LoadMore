package com.talo.loadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.talo.loadmore.databinding.LayoutItemBinding;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Integer> list;


    public MainAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtNumber.setText(String.valueOf(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemBinding binding;

        public ViewHolder(@NonNull LayoutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
