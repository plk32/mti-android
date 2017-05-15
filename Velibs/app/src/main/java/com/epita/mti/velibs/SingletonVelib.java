package com.epita.mti.velibs;

import java.util.ArrayList;

/**
 * Created by Lorris on 12/05/2017.
 */

class SingletonVelib {
    private static final SingletonVelib ourInstance = new SingletonVelib();

    static SingletonVelib getInstance() {
        return ourInstance;
    }

    private SingletonVelib() {
    }

    private ArrayList<Velib> velibs;

    public ArrayList<Velib> getVelibs() {
        return velibs;
    }

    public void setVelibs(ArrayList<Velib> velibArrayList) {
        velibs = velibArrayList;
    }
}
