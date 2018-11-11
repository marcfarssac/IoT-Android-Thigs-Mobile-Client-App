package com.mfarssac.iotblesensors.client.model;

public class BleSensorData {

    Float temperature;
    Long timestamp_temperature;

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Long getTimestamp_temperature() {
        return timestamp_temperature;
    }

    public void setTimestamp_temperature(Long timestamp_temperature) {
        this.timestamp_temperature = timestamp_temperature;
    }
}
