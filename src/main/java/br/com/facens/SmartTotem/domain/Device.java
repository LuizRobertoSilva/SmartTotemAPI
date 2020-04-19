package br.com.facens.SmartTotem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device
implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;

    private Double currentHumidity;

    private Double currentTemperature;

    private Double currentCO2;

    private String latitude;

    private String longitude;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private List<DeviceInformation> deviceInformationList = new ArrayList<>();

    public Device() {
    }

    public Device(Integer id, String name, Double currentHumidity, Double currentTemperature, Double currentCO2, String latitude, String longitude, Region region, List<DeviceInformation> deviceInformationList) {
        this.id = id;
        this.name = name;
        this.currentHumidity = currentHumidity;
        this.currentTemperature = currentTemperature;
        this.currentCO2 = currentCO2;
        this.latitude = latitude;
        this.longitude = longitude;
        this.region = region;
        this.deviceInformationList = deviceInformationList;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(Double currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getCurrentCO2() {
        return currentCO2;
    }

    public void setCurrentCO2(Double currentCO2) {
        this.currentCO2 = currentCO2;
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

    public List<DeviceInformation> getDeviceInformationList() {
        return deviceInformationList;
    }

    public void setDeviceInformationList(List<DeviceInformation> deviceInformationList) {
        this.deviceInformationList = deviceInformationList;
    }
}
