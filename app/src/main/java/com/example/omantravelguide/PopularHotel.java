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

public class PopularHotel extends AppCompatActivity {
    RecyclerView popular_recyclerView;
    //RecyclerView nearest_recyclerView;
    //ArrayList<HotelModel> list;
    ArrayList<HotelModel> arrayList;
    TextView txt,filterbtn,resultmsg;
    NearbyAdapter nearbyAdapter;
    SearchView search_view;
    Dialog dialog;
    RadioGroup radioGroup;
    RadioButton populartbtn,nearestBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_hotel);
        dialog = new Dialog(PopularHotel.this);
        dialog.setContentView(R.layout.custom_dialog2);
        filterbtn=findViewById(R.id.filter);
        resultmsg=findViewById(R.id.textView);
        popular_recyclerView=findViewById(R.id.recycler_view);

        search_view=findViewById(R.id.search_view);

        txt =findViewById(R.id.txt);
        popular_recyclerView.setLayoutManager(new GridLayoutManager(PopularHotel.this,2));
        //popular_recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       // list =new ArrayList<>();

        arrayList=new ArrayList<>();



        search_view.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                nearbyAdapter.getFilter().filter(newText);
                return true;
            }
        });

        txt.setText("Popular Hotels");
        fetchPopular();
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
                          fetchPopular();
                          dialog.dismiss();
                          txt.setText("Popular Hotels");

                      }
                      else
                          if (nearestBtn.isChecked()){
                         // String str_nearest="0";
                          //////////////
                          GpsTracker gpsTracker = new GpsTracker(PopularHotel.this);
                          if (gpsTracker.canGetLocation()) {
                              double latitude = gpsTracker.getLatitude();
                              double longitude = gpsTracker.getLongitude();
                              try {


                                  // String address=getAddress(latitude,longitude);
                                  //showLocation.setText(address);
                              fetchNear(latitude,longitude);
                                  dialog.dismiss();
                                  txt.setText("Nearest Hotels");
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
//                delete_txt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                      //  Toast.makeText(context, "delet", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });

    }

    private void fetchPopular()
    {
        FirebaseDatabase.getInstance().getReference().child("hotels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                 //   if (snapshot1.child("popular").getValue(String.class).equals("1")){

                    arrayList.add(new HotelModel(
                            snapshot1.getKey(),
                            snapshot1.child("hotel_name").getValue(String.class),
                            snapshot1.child("lati").getValue(String.class),
                            snapshot1.child("longi").getValue(String.class),
                            snapshot1.child("rate").getValue(String.class),
                            snapshot1.child("expert_rating").getValue(String.class),
                            snapshot1.child("detail").getValue(String.class),
                            snapshot1.child("address").getValue(String.class),
                            snapshot1.child("imageUrl").getValue(String.class),
                            snapshot1.child("popular").getValue(String.class)


                    ));
             //   }
                }
                nearbyAdapter=new NearbyAdapter(PopularHotel.this,arrayList);
                popular_recyclerView.setAdapter(nearbyAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PopularHotel.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchNear(double curlatitude, double curlongitude)
    {
        FirebaseDatabase.getInstance().getReference().child("hotels").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    double latitude = Double.parseDouble((String) snapshot1.child("lati").getValue(String.class));
                    double longitude = Double.parseDouble(snapshot1.child("longi").getValue(String.class));

                    final long rideEstDistance = (long) CalculationByDistance(new LatLng(curlatitude, curlongitude), new LatLng(latitude, longitude));

                    if (rideEstDistance <= 10) {
                  //  Toast.makeText(PopularHotel.this, "data found", Toast.LENGTH_SHORT).show();
                       arrayList.add(new HotelModel(
                                snapshot1.getKey(),
                                snapshot1.child("hotel_name").getValue(String.class),
                                snapshot1.child("lati").getValue(String.class),
                                snapshot1.child("longi").getValue(String.class),
                                snapshot1.child("rate").getValue(String.class),
                                snapshot1.child("expert_rating").getValue(String.class),
                                snapshot1.child("detail").getValue(String.class),
                                snapshot1.child("address").getValue(String.class),
                                snapshot1.child("imageUrl").getValue(String.class),
                               snapshot1.child("popular").getValue(String.class)


                        ));

                  }

                }
              //  if (arrayList.size()!=0){
                    nearbyAdapter=new NearbyAdapter(PopularHotel.this,arrayList);
                    popular_recyclerView.setAdapter(nearbyAdapter);
               // }else {
                  //  resultmsg.setVisibility(View.VISIBLE);
                   // resultmsg.setText("No Result Found");

               // }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PopularHotel.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PopularHotel.this,HomeActivity.class));
        finish();
    }

}