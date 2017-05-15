package com.epita.mti.velibs;

import java.sql.Date;
import java.util.ArrayList;

public class Velib {
    private final String recordid;
    private final VelibFields fields;

    public String getRecordid() {
        return recordid;
    }

    public VelibFields getFields() {
        return fields;
    }

    public Velib(String recordid, VelibFields fields) {
        this.recordid = recordid;
        this.fields = fields;
    }
}
