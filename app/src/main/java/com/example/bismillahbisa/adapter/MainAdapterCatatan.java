package com.example.bismillahbisa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bismillahbisa.view.fragment.MainContactCatatan;
import com.example.bismillahbisa.R;
import com.example.bismillahbisa.entity.DataIbadah;

import java.util.List;

public class MainAdapterCatatan extends RecyclerView.Adapter<MainAdapterCatatan.viewHolder> {
    Context context;
    List<DataIbadah> list;
    MainContactCatatan.view mView;

    public MainAdapterCatatan(Context context, List<DataIbadah> list, MainContactCatatan.view mView){
        this.context = context;
        this.list = list;
        this.mView = mView;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_catatan,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataIbadah item = list.get(position);
        holder.tvDate.setText(item.getDate());
        holder.tvFardhu.setText(item.getFardhu());
        holder.tvSunah.setText(item.getSunah());
        holder.tvPuasa.setText(item.getPuasa());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.editData(item);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mView.deleteData(item);
                return true;
            }
        });
    }

    public int getItemCount() {
        return  list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, tvDate, tvFardhu, tvSunah, tvPuasa;
        CardView cardView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvFardhu = itemView.findViewById(R.id.tvFardhu);
            tvSunah = itemView.findViewById(R.id.tvSunah);
            tvPuasa = itemView.findViewById(R.id.tvPuasa);
            cardView = itemView.findViewById(R.id.cv_item);
        }

    }
}
