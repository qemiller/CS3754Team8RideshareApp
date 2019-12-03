/*
 * Created by Justin Kennedy on 2019.12.01  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jusmk96
 */
@Entity
@Table(name = "UserRides")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRides.findAll", query = "SELECT u FROM UserRides u")
    , @NamedQuery(name = "UserRides.findById", query = "SELECT u FROM UserRides u WHERE u.id = :id")
    , @NamedQuery(name = "UserRides.findByAllRidesID", query = "SELECT u FROM UserRides u WHERE u.allRides_id = :allRidesId")
    , @NamedQuery(name = "UserRides.findByDriverUsername", query = "SELECT u FROM UserRides u WHERE u.driverUsername = :driverUsername")
    , @NamedQuery(name = "UserRides.findByPassanger1Id", query = "SELECT u FROM UserRides u WHERE u.passanger1Id = :passanger1Id")
    , @NamedQuery(name = "UserRides.findByPassanger2Id", query = "SELECT u FROM UserRides u WHERE u.passanger2Id = :passanger2Id")
    , @NamedQuery(name = "UserRides.findByPassanger3Id", query = "SELECT u FROM UserRides u WHERE u.passanger3Id = :passanger3Id")
    , @NamedQuery(name = "UserRides.findByPassanger4Id", query = "SELECT u FROM UserRides u WHERE u.passanger4Id = :passanger4Id")
    , @NamedQuery(name = "UserRides.findByPassanger5Id", query = "SELECT u FROM UserRides u WHERE u.passanger5Id = :passanger5Id")
    , @NamedQuery(name = "UserRides.findByPassanger6Id", query = "SELECT u FROM UserRides u WHERE u.passanger6Id = :passanger6Id")
    , @NamedQuery(name = "UserRides.findBySeatsAvailable", query = "SELECT u FROM UserRides u WHERE u.seatsAvailable = :seatsAvailable")
    , @NamedQuery(name = "UserRides.findByStartingLocation", query = "SELECT u FROM UserRides u WHERE u.startingLocation = :startingLocation")
    , @NamedQuery(name = "UserRides.findByEndingLocation", query = "SELECT u FROM UserRides u WHERE u.endingLocation = :endingLocation")
    , @NamedQuery(name = "UserRides.findByTripDate", query = "SELECT u FROM UserRides u WHERE u.tripDate = :tripDate")
    , @NamedQuery(name = "UserRides.findByNumberOfPassangers", query = "SELECT u FROM UserRides u WHERE u.numberOfPassangers = :numberOfPassangers")
    , @NamedQuery(name = "UserRides.findByUserPrimaryKey", query = "SELECT u FROM UserRides u WHERE u.userId.id = :primaryKey")
})
public class UserRides implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allRides_id")
    private int allRides_id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "driver_username")
    private String driverUsername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_1_id")
    private int passanger1Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_2_id")
    private int passanger2Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_3_id")
    private int passanger3Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_4_id")
    private int passanger4Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_5_id")
    private int passanger5Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "passanger_6_id")
    private int passanger6Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "seats_available")
    private int seatsAvailable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "starting_location")
    private String startingLocation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ending_location")
    private String endingLocation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trip_date")
    @Temporal(TemporalType.DATE)
    private Date tripDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_passangers")
    private int numberOfPassangers;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public UserRides() {
    }

    public UserRides(Integer id) {
        this.id = id;
    }

    public UserRides(Integer id, Integer allRidesId, String driverUsername, int passanger1Id, int passanger2Id, int passanger3Id, int passanger4Id, int passanger5Id, int passanger6Id, int seatsAvailable, String startingLocation, String endingLocation, Date tripDate, int numberOfPassangers) {
        this.id = id;
        this.allRides_id = allRidesId;
        this.driverUsername = driverUsername;
        this.passanger1Id = passanger1Id;
        this.passanger2Id = passanger2Id;
        this.passanger3Id = passanger3Id;
        this.passanger4Id = passanger4Id;
        this.passanger5Id = passanger5Id;
        this.passanger6Id = passanger6Id;
        this.seatsAvailable = seatsAvailable;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.tripDate = tripDate;
        this.numberOfPassangers = numberOfPassangers;
    }

    public void setAll(Integer allRidesId, String driverUsername, 
            int passanger1Id, int passanger2Id, int passanger3Id, 
            int passanger4Id, int passanger5Id, int passanger6Id, 
            int seatsAvailable, String startingLocation, String endingLocation, 
            Date tripDate, int numberOfPassangers){
        this.allRides_id = allRidesId;
        this.driverUsername = driverUsername;
        this.passanger1Id = passanger1Id;
        this.passanger2Id = passanger2Id;
        this.passanger3Id = passanger3Id;
        this.passanger4Id = passanger4Id;
        this.passanger5Id = passanger5Id;
        this.passanger6Id = passanger6Id;
        this.seatsAvailable = seatsAvailable;
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.tripDate = tripDate;
        this.numberOfPassangers = numberOfPassangers;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAllRides_id() {
        return allRides_id;
    }

    public void setAllRides_id(int allRides_id) {
        this.allRides_id = allRides_id;
    }

    
    public String getDriverUsername() {
        return driverUsername;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public int getPassanger1Id() {
        return passanger1Id;
    }

    public void setPassanger1Id(int passanger1Id) {
        this.passanger1Id = passanger1Id;
    }

    public int getPassanger2Id() {
        return passanger2Id;
    }

    public void setPassanger2Id(int passanger2Id) {
        this.passanger2Id = passanger2Id;
    }

    public int getPassanger3Id() {
        return passanger3Id;
    }

    public void setPassanger3Id(int passanger3Id) {
        this.passanger3Id = passanger3Id;
    }

    public int getPassanger4Id() {
        return passanger4Id;
    }

    public void setPassanger4Id(int passanger4Id) {
        this.passanger4Id = passanger4Id;
    }

    public int getPassanger5Id() {
        return passanger5Id;
    }

    public void setPassanger5Id(int passanger5Id) {
        this.passanger5Id = passanger5Id;
    }

    public int getPassanger6Id() {
        return passanger6Id;
    }

    public void setPassanger6Id(int passanger6Id) {
        this.passanger6Id = passanger6Id;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getEndingLocation() {
        return endingLocation;
    }

    public void setEndingLocation(String endingLocation) {
        this.endingLocation = endingLocation;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    public void setNumberOfPassangers(int numberOfPassangers) {
        this.numberOfPassangers = numberOfPassangers;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRides)) {
            return false;
        }
        UserRides other = (UserRides) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
