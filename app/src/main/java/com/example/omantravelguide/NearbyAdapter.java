package com.example.omantravelguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder> {
    Context context;
    ArrayList<HotelModel> arrayList;
    ArrayList<HotelModel> list2;

    public NearbyAdapter(Context context, ArrayList<HotelModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        list2 = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_deign_hotel,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotelModel hotelModel=arrayList.get(position);
        holder.hotelName.setText(hotelModel.hotel_name);
        holder.pricing.setText("OMR"+hotelModel.rate);
        holder.locationtxt.setText(hotelModel.address);
     //   holder.rating.setText(hotelModel.expert_rating);
        Glide.with(context).load(hotelModel.imgUri).into(holder.imageView);

        if (hotelModel.popular.equals("0")){
            holder.locationtxt.setVisibility(View.GONE);
        }else {
            holder.locationtxt.setVisibility(View.VISIBLE);

        }



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
        CardView cardView;
        TextView hotelName,locationtxt,pricing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            hotelName=itemView.findViewById(R.id.txt_hotel_name);
            locationtxt=itemView.findViewById(R.id.txt_hotel_location);
          //  rating=itemView.findViewById(R.id.txt_rating);
            pricing=itemView.findViewById(R.id.txt_price);
            cardView=itemView.findViewById(R.id.cardbtn);
        }
    }


    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<HotelModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(list2);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (HotelModel item : list2) {
                    if (item.getHotel_name().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
