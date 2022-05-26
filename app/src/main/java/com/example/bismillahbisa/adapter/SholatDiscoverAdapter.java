package com.example.bismillahbisa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bismillahbisa.R;
import com.example.bismillahbisa.model.DataAdapter;
import com.example.bismillahbisa.model.sholat.DataItem;

import java.util.ArrayList;
import java.util.List;

public class SholatDiscoverAdapter extends RecyclerView.Adapter<SholatDiscoverAdapter.ViewHolder> {
    private List<DataAdapter> data = new ArrayList<>();

    public void setData(List<DataAdapter> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SholatDiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SholatDiscoverAdapter.ViewHolder holder, int position) {
        holder.txtNameTiming.setText(data.get(position).getName());
        holder.txtClockTiming.setText(data.get(position).getClock());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameTiming, txtClockTiming;
        CardView cvSholat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvSholat = itemView.findViewById(R.id.itemlist_cv);
            txtNameTiming = itemView.findViewById(R.id.txtNameTiming);
            txtClockTiming = itemView.findViewById(R.id.txtClockTiming);
        }
    }
}
