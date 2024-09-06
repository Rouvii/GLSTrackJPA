package org.example.persistence.daos.interfaces;

public interface ILocationDAO {


    //get longitude
    double getLongitude();

    //get latitude
    double getLatitude();

    //get address
    String getAddress();

    //set longitude
    void setLongitude(double longitude);

    //set latitude
    void setLatitude(double latitude);

    //set address
    void setAddress(String address);





}
