package com.example.demo.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Station implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "station_id", nullable = false)
    private Long id;
    private  String stationName;
    @OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
    Image image;
    private String stationAddress;
    private String stationPricing;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }





    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationPricing() {
        return stationPricing;
    }

    public void setStationPricing(String stationPricing) {
        this.stationPricing = stationPricing;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
