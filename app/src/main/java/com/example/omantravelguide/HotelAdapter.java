package com.example.omantravelguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
  Context context;
  ArrayList<HotelModel> arrayList;

    public HotelAdapter(Context context, ArrayList<HotelModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelModel hotelModel=arrayList.get(position);
        holder.nametxt.setText(hotelModel.hotel_name);
        holder.pricingTxt.setText("OMR"+hotelModel.rate);
        Glide.with(context).load(hotelModel.imgUri).into(holder.imageView);

holder.cardView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("MYDATA", (Serializable) hotelModel);
        Intent intent=new Intent(context, HotelActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
});
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nametxt,pricingTxt;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img_hotel_popular);
            nametxt =itemView.findViewById(R.id.txt_hotel_name);
            pricingTxt=itemView.findViewById(R.id.txt_price);
            cardView=itemView.findViewById(R.id.cardPupular);
        }
    }
}
