package fi.muni.cz.pa165.travelagency.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * Excursion entity for the Travel Angecy project.
 *
 * @author (name = "Nermin Jukan", UCO = "<473370>")
 */


@Entity
public class Excursion implements Serializable {

    /**
     * Creates an Excursion with given parameters
     * @param description given description
     * @param destination given destination
     * @param duration given duration
     * @param date given date
     * @param price given price
     * @param trips given trips
     */
    public Excursion(String description, String destination, Integer duration,
                     Date date, BigDecimal price, Trip... trips) {
        this();
        setDescription(description);
        setDestination(destination);
        setDuration(duration);
        setExcursionDate(date);
        setPrice(price);
        addAllTrips(Arrays.asList(trips));
    }

    /**
     * Creates an empty Excursion
     */
    public Excursion() {
    }

    /*
     * The ID of the Excursion.
     * 
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    /*
     * The date at which the Excursion is taking place.
     */
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date excursionDate;
    
    /*
     * The duration of the Excursion.
     */
    @NotNull
    private Integer duration;
    
    /*
     * The description of the Excursion.
     */
    @NotNull
    private String description;
    
    /*
     * The destination of the Excursion.
     */
    @NotNull
    private String destination;
    
    
    /*
     * The price of the Excursion.
     */
    @NotNull
    private BigDecimal price;
    
    /*
     * The Trips set of the Excursion.
     */
    @ManyToMany(mappedBy = "excursions")
    private Set<Trip> trips = new HashSet<>();

    /**
     * Sets the ID of the Excursion.
     *
     * @param id Excursion ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method returns Excursion ID.
     *
     * @return Excursion ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Method returns Excursion date.
     *
     * @return Excursion date
     */
    public Date getExcursionDate() {
        return excursionDate;
    }

    /**
     * Sets the date of the Excursion.
     *
     * @param excursionDate Excursion date
     */
    public void setExcursionDate(Date excursionDate) {
        this.excursionDate = excursionDate;
    }

    /**
     * Method returns Excursion duration.
     *
     * @return Excursion duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the Excursion.
     *
     * @param duration Excursion duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Method returns Excursion description.
     *
     * @return Excursion description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the Excursion.
     *
     * @param description Excursion description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method returns Excursion destination.
     *
     * @return Excursion destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the Excursion.
     *
     * @param destination Excursion destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Method returns Excursion price.
     *
     * @return Excursion price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the Excursion.
     *
     * @param price Excursion price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Method returns Excursion hash code.
     *
     * @return Excursion hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.excursionDate);
        hash = 29 * hash + Objects.hashCode(this.duration);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.destination);
        hash = 29 * hash + Objects.hashCode(this.price);
        return hash;
    }
    
    /**
     * Method returns true if two Excursions are equal, false otherwise.
     * The ID field MUST BE SET.
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Excursion)) {
            return false;
        }
        final Excursion other = (Excursion) obj;
        if (!Objects.equals(this.description, other.getDescription())) {
            return false;
        }
        if (!Objects.equals(this.destination, other.getDestination())) {
            return false;
        }
        if (!Objects.equals(this.excursionDate, other.getExcursionDate())) {
            return false;
        }
        if (!Objects.equals(this.duration, other.getDuration())) {
            return false;
        }
        if (!Objects.equals(this.price, other.getPrice())) {
            return false;
        }
        return true;
    }
    
    
    

    /**
     * Method returns a string presentation of the Excursion.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "fi.muni.cz.pa165.travelagency.entity.Excursion[ id=" + id + " ]";
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }



    /**
     * Returns unmodifiable set of trips that include this excursion.
     * @return unmodifiable set of trips that include this excursion.
     */
    public Set<Trip> getTrips() {
        return trips;
    }

    /**
     * Adds given trip to the set of trips that include this excursion.
     * @param trip trip to be added
     */
    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    /**
     * Adds all trips from the given collection to the set of trips that include this excursion.
     * @param trips collection of trips to be added
     */
    public void addAllTrips(Collection<Trip> trips) {
        this.trips.addAll(trips);
    }

    /**
     * Removes given trip from the set of trips that include this excursion.
     * @param trip trip to be removed
     */
    public void removeTrip(Trip trip) {
        this.trips.remove(trip);
    }
    
}
