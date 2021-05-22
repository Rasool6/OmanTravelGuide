package com.example.omantravelguide;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class COnfirm_frgadmin extends Fragment {
    RecyclerView recyclerView;
    COnfirm_AdapterAdmin cOnfirm_adapter;
    ArrayList<BookingModel> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_c_onfirm_frgadmin, container, false);
        recyclerView=view.findViewById(R.id.recycler_viewPendingAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();
        fetchConfirmOrder();

        return view;
    }

    private void fetchConfirmOrder() {
        FirebaseDatabase.getInstance().getReference().child("Booking").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    if (snapshot1.child("booking_status").getValue(String.class).equals("1")){

                        list.add(new BookingModel(
                                snapshot1.getKey(),
                                snapshot1.child("hotel_id").getValue(String.class),
                                snapshot1.child("hotel_name").getValue(String.class),
                                snapshot1.child("hotelpriceing").getValue(String.class),
                                snapshot1.child("fullName").getValue(String.class),
                                snapshot1.child("email").getValue(String.class),
                                snapshot1.child("phoneNo").getValue(String.class),
                                snapshot1.child("roomtype").getValue(String.class),
                                snapshot1.child("arrival_Date").getValue(String.class),
                                snapshot1.child("departure_date").getValue(String.class),
                                snapshot1.child("noOfGuests").getValue(String.class),
                                snapshot1.child("booking_status").getValue(String.class),
                                snapshot1.child("requestType").getValue(String.class)


                        ));
                    }
                }
                cOnfirm_adapter=new COnfirm_AdapterAdmin(getContext(),list);
                recyclerView.setAdapter(cOnfirm_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}