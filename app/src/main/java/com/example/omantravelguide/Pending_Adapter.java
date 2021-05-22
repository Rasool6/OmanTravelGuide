package com.example.omantravelguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pending_Adapter extends RecyclerView.Adapter<Pending_Adapter.ViewHolder> {
    Context context;
    ArrayList<BookingModel> arrayList;

    public Pending_Adapter(Context context, ArrayList<BookingModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_design_confirm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookingModel bookingModel=arrayList.get(position);
        holder.orderPersonName.setText(bookingModel.fullName);
        holder.hotelName.setText(bookingModel.hotel_name);
        holder.pricing.setText("OMR "+bookingModel.hotelpriceing);
        holder.arrivaldate.setText(bookingModel.arrival_Date);
        holder.departureDate.setText(bookingModel.departure_date);
        holder.phoneNo.setText(bookingModel.phoneNo);
        holder.roomType.setText(bookingModel.roomtype);
        holder.email.setText(bookingModel.email);
        holder.noOfGuests.setText(bookingModel.noOfGuests);
        holder.notiftxt.setText(bookingModel.requestType);

        if (bookingModel.booking_status.equals("0")){
            holder.statusTxt.setText("Pending");
        }else {

        }




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView imageView;
//        CardView cardView;
     TextView hotelName,orderPersonName,pricing,arrivaldate,
        departureDate,phoneNo,roomType,noOfGuests,email,statusTxt,
        notiftxt,acceptbn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderPersonName=itemView.findViewById(R.id.orderbyName);
            hotelName=itemView.findViewById(R.id.textView15);
            arrivaldate=itemView.findViewById(R.id.textView14);
            departureDate=itemView.findViewById(R.id.textView27);
            pricing=itemView.findViewById(R.id.textView13);
            phoneNo=itemView.findViewById(R.id.textView20);
            roomType=itemView.findViewById(R.id.textView18);
            noOfGuests=itemView.findViewById(R.id.textView22);
            email=itemView.findViewById(R.id.textView26);
            notiftxt=itemView.findViewById(R.id.notiValue);
          //  cancelbtn=itemView.findViewById(R.id.textView24);
            statusTxt=itemView.findViewById(R.id.textView24);
        }
    }
}
