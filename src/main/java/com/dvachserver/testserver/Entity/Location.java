package com.dvachserver.testserver.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "latitude")
    private Long latitude;
    @Column(name = "longitude")
    private Long longitude;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private OwnerContact ownerContact;

    public Location() {
        this.date = new Date();
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OwnerContact getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(OwnerContact ownerContact) {
        this.ownerContact = ownerContact;
    }
}
