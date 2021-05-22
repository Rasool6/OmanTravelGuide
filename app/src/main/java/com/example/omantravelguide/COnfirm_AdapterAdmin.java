package com.example.omantravelguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class COnfirm_AdapterAdmin extends RecyclerView.Adapter<COnfirm_AdapterAdmin.ViewHolder> {
    Context context;
    ArrayList<BookingModel> arrayList;

    public COnfirm_AdapterAdmin(Context context, ArrayList<BookingModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_design_confirmadmin,parent,false);
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
        holder.notiTxt.setText(bookingModel.requestType);

        if (bookingModel.booking_status.equals("1")){
                 holder.cancelbtn.setVisibility(View.GONE);
                 holder.acceptbn.setVisibility(View.GONE);
        }else {
            holder.cancelbtn.setVisibility(View.VISIBLE);
            holder.acceptbn.setVisibility(View.VISIBLE);
        }

        holder.acceptbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Booking").child(bookingModel.booking_id).child("booking_status").setValue("1").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        context.startActivity(new Intent(context,AdminDashboard.class));
                        ((Activity)context).finish();
                        Toast.makeText(context, "Order accepted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        holder.cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Booking").child(bookingModel.booking_id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        context.startActivity(new Intent(context,AdminDashboard.class));
                        ((Activity)context).finish();
                        Toast.makeText(context, "Order Canceled Successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView imageView;
//        CardView cardView;
        TextView hotelName,orderPersonName,pricing,arrivaldate,departureDate,phoneNo,roomType,noOfGuests,email,notiTxt,cancelbtn,acceptbn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderPersonName=itemView.findViewById(R.id.orderbyName);
            hotelName=itemView.findViewById(R.id.textView15);
            arrivaldate=itemView.findViewById(R.id.textView14);
            departureDate=itemView.findViewById(R.id.textView16);
            pricing=itemView.findViewById(R.id.textView13);
            phoneNo=itemView.findViewById(R.id.textView20);
            roomType=itemView.findViewById(R.id.textView18);
            noOfGuests=itemView.findViewById(R.id.textView22);
            email=itemView.findViewById(R.id.textView26);
            acceptbn=itemView.findViewById(R.id.textView23);
            cancelbtn=itemView.findViewById(R.id.textView24);
          notiTxt=itemView.findViewById(R.id.valueNoti);


        }
    }
}
