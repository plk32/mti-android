package com.epita.mti.velibs;

import java.util.ArrayList;

/**
 * Created by moussaoufkir on 15/05/2017.
 */

public class VelibResponse {
    private final ArrayList<Velib> records;

    public VelibResponse(ArrayList<Velib> records) {
        this.records = records;
    }

    public ArrayList<Velib> getRecords() {
        return records;
    }
}
