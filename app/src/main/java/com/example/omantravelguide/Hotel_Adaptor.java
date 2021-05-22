package com.example.omantravelguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Hotel_Adaptor extends RecyclerView.Adapter<Hotel_Adaptor.ViewHolder> {
    Context context;
    List<HotelModel> list;

    public Hotel_Adaptor(Context context, List<HotelModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hotel_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelModel hotelModel=list.get(position);
        holder.hotelName.setText(hotelModel.hotel_name);
        holder.hotelAddress.setText(hotelModel.address);
        holder.hotelRating.setText(hotelModel.expert_rating);
        holder.hotalPrice.setText("$"+hotelModel.rate);
        holder.hotelDetal.setText(hotelModel.detail);
        Glide.with(context).load(hotelModel.imgUri).into(holder.image_view);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_view;
        TextView hotelName,hotelAddress,hotelRating,hotalPrice,hotelDetal;
        Button checkAvailabilitybtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view=itemView.findViewById(R.id.image_view);
            hotelName=itemView.findViewById(R.id.txt_location);
            hotelAddress=itemView.findViewById(R.id.txt_hotel);
            hotelRating=itemView.findViewById(R.id.ratingId);
            hotalPrice=itemView.findViewById(R.id.hotelPricing);
            hotelDetal=itemView.findViewById(R.id.txt_booking_paragraph1);
            checkAvailabilitybtn=itemView.findViewById(R.id.checkAvailability);
        }
    }
}
