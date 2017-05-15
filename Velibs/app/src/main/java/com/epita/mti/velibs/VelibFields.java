package com.epita.mti.velibs;

public class VelibFields {
    private final String name;
    private final String status;
    private final int bike_stands ;
    private final int available_bike_stands;
    private final String address;
    private final String last_update;

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getBike_stands() {
        return bike_stands;
    }

    public int getAvailable_bike_stands() {
        return available_bike_stands;
    }

    public String getAddress() {
        return address;
    }

    public String getLastUpdate() {
        return last_update;
    }

    public VelibFields(String name, String status, int bike_stands,
                       int available_bike_stands, String address, String last_update) {
        this.name = name;
        this.status = status;
        this.bike_stands = bike_stands;
        this.available_bike_stands = available_bike_stands;
        this.address = address;
        this.last_update = last_update;
    }
}
