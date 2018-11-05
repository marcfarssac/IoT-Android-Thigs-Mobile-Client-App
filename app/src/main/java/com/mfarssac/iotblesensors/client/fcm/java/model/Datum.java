package com.mfarssac.iotblesensors.client.fcm.java.model;

public class Datum
{
    private Object timestamp_temperature;

    public Object getTimestampTemperature() { return this.timestamp_temperature; }

    public void setTimestampTemperature(Object timestamp_temperature) { this.timestamp_temperature = timestamp_temperature; }

    private double temperature;

    public double getTemperature() { return this.temperature; }

    public void setTemperature(double temperature) { this.temperature = temperature; }
}