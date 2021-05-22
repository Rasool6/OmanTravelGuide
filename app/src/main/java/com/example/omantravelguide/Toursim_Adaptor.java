package com.example.omantravelguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Toursim_Adaptor extends RecyclerView.Adapter<Toursim_Adaptor.ViewHolder> {
    Context context;
    List<TourismModel> list;
    List<TourismModel> list2;

    public Toursim_Adaptor(Context context, List<TourismModel> list) {
        this.context = context;
        this.list = list;
        list2 = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tourrism_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourismModel tourismModel=list.get(position);
        holder.name.setText(tourismModel.name);
        holder.price.setText(tourismModel.price);

        Glide.with(context).load(tourismModel.imgUrl).into(holder.image_view);
holder.readMoreBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("MYDATA", (Serializable) tourismModel);
        Intent intent=new Intent(context, ToursimDeatilActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_view;
        TextView name,price,readMoreBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_view=itemView.findViewById(R.id.imageView4);
            name=itemView.findViewById(R.id.textView3);
            price=itemView.findViewById(R.id.textView4);
            readMoreBtn=itemView.findViewById(R.id.textView5);
        }
    }

    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<TourismModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(list2);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (TourismModel item : list2) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
