package com.example.rmider.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rmider.R;
import com.example.rmider.model.Fuel;

import java.util.ArrayList;

public class FuelAdapter extends RecyclerView.Adapter<FuelAdapter.ViewHolder> {

    private ArrayList<Fuel> arrayFuel;

    public FuelAdapter(ArrayList<Fuel> arrayFuel) {
        this.arrayFuel = arrayFuel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_fuel,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.imgPicture)
                .load(arrayFuel.get(position).getPicture())
                .into(holder.imgPicture);

        holder.txtName.setText(arrayFuel.get(position).getName());
        holder.txtCount.setText(arrayFuel.get(position).getCount() + "");
        holder.txtPrice.setText("Đơn giá: " + arrayFuel.get(position).getPrice() + " ");
        holder.txtAllPrice.setText("Tổng tiền: " + (arrayFuel.get(position).getPrice() * arrayFuel.get(position).getCount()) + " VND");
    }

    @Override
    public int getItemCount() {
        return arrayFuel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPicture,imgRemove,imgAdd;
        TextView txtName,txtCount,txtPrice,txtAllPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            imgPicture = itemView.findViewById(R.id.imgPicture);
            imgRemove = itemView.findViewById(R.id.imgRemove);
            txtCount = itemView.findViewById(R.id.txtCount);
            imgAdd = itemView.findViewById(R.id.imgAdd);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtAllPrice = itemView.findViewById(R.id.txtAllPrice);

            imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arrayFuel.get(getAdapterPosition()).getCount() > 0){
                        arrayFuel.get(getAdapterPosition()).setCount(arrayFuel.get(getAdapterPosition()).getCount() - 1);
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

            imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (arrayFuel.get(getAdapterPosition()).getCount() < 99){
                        arrayFuel.get(getAdapterPosition()).setCount(arrayFuel.get(getAdapterPosition()).getCount() + 1);
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });
        }
    }
}
