package com.example.omantravelguide;

public class BookingModel {
    public  String booking_id;
    public  String hotel_id;
    public  String hotel_name;
    public  String hotelpriceing;
    public  String fullName;
    public  String email;
    public  String phoneNo;
    public  String roomtype;
    public  String arrival_Date;
    public  String departure_date;
    public  String noOfGuests;
    public  String booking_status;
    public  String requestType;

    public BookingModel(String booking_id, String hotel_id, String hotel_name, String hotelpriceing,
                        String fullName, String email, String phoneNo, String roomtype, String arrival_Date,
                        String departure_date, String noOfGuests, String booking_status,String requestType) {
        this.booking_id = booking_id;
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotelpriceing = hotelpriceing;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.roomtype = roomtype;
        this.arrival_Date = arrival_Date;
        this.departure_date = departure_date;
        this.noOfGuests = noOfGuests;
        this.booking_status = booking_status;
    }

    public BookingModel(String hotel_id, String hotel_name, String hotelpriceing, String fullName,
                        String email, String phoneNo, String roomtype, String arrival_Date,
                        String departure_date, String noOfGuests, String booking_status
    ,String requestType) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotelpriceing = hotelpriceing;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.roomtype = roomtype;
        this.arrival_Date = arrival_Date;
        this.departure_date = departure_date;
        this.noOfGuests = noOfGuests;
        this.booking_status = booking_status;
        this.requestType = requestType;
    }
}
