package br.com.facens.SmartTotem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "region")
    @JsonIgnore
    List<DeviceInformation> deviceInformationList = new ArrayList<>();

    @OneToMany(mappedBy = "region")
    @JsonIgnore
    List<Device> deviceList = new ArrayList<>();

    public Region() {
    }

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public List<DeviceInformation> getDeviceInformationList() {
        return deviceInformationList;
    }

    public void setDeviceInformationList(List<DeviceInformation> deviceInformationList) {
        this.deviceInformationList = deviceInformationList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
