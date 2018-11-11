package com.mfarssac.iotblesensors.client.model;

import java.util.List;

public class IoTTelemetryData {

    String deviceId;
    String deviceNumId;
    String deviceRegistryId;
    List<BleSensorData> telemetryData;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumId() {
        return deviceNumId;
    }

    public void setDeviceNumId(String deviceNumId) {
        this.deviceNumId = deviceNumId;
    }

    public String getDeviceRegistryId() {
        return deviceRegistryId;
    }

    public void setDeviceRegistryId(String deviceRegistryId) {
        this.deviceRegistryId = deviceRegistryId;
    }

    public List<BleSensorData> getTelemetryData() {
        return telemetryData;
    }

    public void setTelemetryData(List<BleSensorData> telemetryData) {
        this.telemetryData = telemetryData;
    }
}
