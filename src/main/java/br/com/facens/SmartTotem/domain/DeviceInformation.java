package br.com.facens.SmartTotem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class DeviceInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double temperature;

    private Double humidity;

    private Double co2;

    private String latitude;

    private String longitude;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    private Date loggedAt;

    public DeviceInformation() {
    }

    public DeviceInformation(Integer id, Double temperature, Double humidity, Double co2, String latitude, String longitude, Region region, Device device, Date loggedAt) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.co2 = co2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.device = device;
        this.loggedAt = loggedAt;
    }

    public DeviceInformation(Device device) {
        this.temperature = device.getCurrentTemperature();
        this.humidity = device.getCurrentHumidity();
        this.co2 = device.getCurrentCO2();
        this.latitude = device.getLatitude();
        this.longitude = device.getLongitude();
        this.region = device.getRegion();
        this.device = device;
        this.loggedAt = new Date();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Date getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(Date loggedAt) {
        this.loggedAt = loggedAt;
    }
}
