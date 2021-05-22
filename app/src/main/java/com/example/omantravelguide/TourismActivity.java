package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TourismActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Toursim_Adaptor toursim_adaptor;
    ArrayList<TourismModel> list;

    SearchView search_view;
    Dialog dialog;
    TextView filterbtn,txt,resulttxt;
    RadioGroup radioGroup;
    RadioButton populartbtn,nearestBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism);
        dialog = new Dialog(TourismActivity.this);
        dialog.setContentView(R.layout.custom_dialog2);
        filterbtn=findViewById(R.id.filter);
        recyclerView=findViewById(R.id.recyclerTour);
        search_view=findViewById(R.id.search_view);
        resulttxt=findViewById(R.id.textView6);

        txt =findViewById(R.id.txt);

        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();



        fetchConfirmOrder();
        filterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setCancelable(true);

        radioGroup = dialog.findViewById(R.id.radioGroup);
        populartbtn = dialog.findViewById(R.id.popular);
        nearestBtn = dialog.findViewById(R.id.nearest);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (populartbtn.isChecked()){
                    //  String str_popular="1";
                    fetchConfirmOrder();
                    dialog.dismiss();
                txt.setText("Popular");

                }
                else
                if (nearestBtn.isChecked()){
                    // String str_nearest="0";
                    //////////////
                    GpsTracker gpsTracker = new GpsTracker(TourismActivity.this);
                    if (gpsTracker.canGetLocation()) {
                        double latitude = gpsTracker.getLatitude();
                        double longitude = gpsTracker.getLongitude();
                        try {


                            // String address=getAddress(latitude,longitude);
                            //showLocation.setText(address);
                            fetchnearest(latitude,longitude);
                            dialog.dismiss();
                         txt.setText("Nearest Results");
                        } catch (RuntimeException e) {
                            e.printStackTrace();
                        }

                    } else {
                        gpsTracker.showSettingsAlert();
                    }
                    //////////////////////////
                }
            }
        });

                    }
                });
        //////////////
        search_view.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                toursim_adaptor.getFilter().filter(newText);
                return true;
            }
        });
///////////////////////////
    }
    private void fetchConfirmOrder() {
        FirebaseDatabase.getInstance().getReference().child("Tourism").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    if (snapshot1.child("popular").getValue(String.class).equals("1")){

                        list.add(new TourismModel(
                                snapshot1.getKey(),
                                snapshot1.child("name").getValue(String.class),
                                snapshot1.child("price").getValue(String.class),
                                snapshot1.child("tourDetail").getValue(String.class),
                                snapshot1.child("imgUrl").getValue(String.class),
                                snapshot1.child("include").getValue(String.class),
                                snapshot1.child("notInclude").getValue(String.class)
                                ));
                    }
             }
                toursim_adaptor=new Toursim_Adaptor(TourismActivity.this,list);
                recyclerView.setAdapter(toursim_adaptor);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TourismActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchnearest(double curlatitude, double curlongitude) {

        FirebaseDatabase.getInstance().getReference().child("Tourism").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    double latitude = Double.parseDouble((String) snapshot1.child("include").getValue(String.class));
                    double longitude = Double.parseDouble(snapshot1.child("notInclude").getValue(String.class));

                    final long rideEstDistance = (long) CalculationByDistance(new LatLng(curlatitude, curlongitude), new LatLng(latitude, longitude));

                   if (rideEstDistance <= 30) {
                        list.add(new TourismModel(
                                snapshot1.getKey(),
                                snapshot1.child("name").getValue(String.class),
                                snapshot1.child("price").getValue(String.class),
                                snapshot1.child("tourDetail").getValue(String.class),
                                snapshot1.child("imgUrl").getValue(String.class),
                                snapshot1.child("include").getValue(String.class),
                                snapshot1.child("notInclude").getValue(String.class)
                                ));
                    }
             }
                if (list.size()!=0){
                toursim_adaptor=new Toursim_Adaptor(TourismActivity.this,list);
                recyclerView.setAdapter(toursim_adaptor);
            }
            else {
                resulttxt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TourismActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void back3(View view) {
//        startActivity(new Intent(TourismActivity.this,HomeActivity.class));
//        finish();
//    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TourismActivity.this,HomeActivity.class));
        finish();
    }
    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);
        // return Radius * c;

        return kmInDec;
    }

}