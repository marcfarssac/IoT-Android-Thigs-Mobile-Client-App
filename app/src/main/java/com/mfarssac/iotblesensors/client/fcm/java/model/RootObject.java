package com.mfarssac.iotblesensors.client.fcm.java.model;

import java.util.ArrayList;

public class RootObject
{
    private ArrayList<Datum> data;

    public ArrayList<Datum> getData() { return this.data; }

    public void setData(ArrayList<Datum> data) { this.data = data; }
}