package fi.muni.cz.pa165.travelagency.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.NotNull;

/**
 * Basic Excursion DTO for the Travel Angecy project.
 * 
 * @author (name = "Nermin Jukan", UCO = "<473370>")
 */

public class ExcursionDTO {
    
    /*
     * The ID of the Excursion.
     */
    @NotNull
    private Long id;
    
    /*
     * The date at which the Excursion is taking place.
     */
    @NotNull
    private Date excursionDate;
    
    /*
     * The description of the Excursion.
     */
    @NotNull
    private String description;
    
    /*
     * The duration of the Excursion.
     */
    @NotNull
    private Integer duration;
    
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
    private Set<TripDTO> trips = new HashSet<>();
    
    /**
     * Empty constructor.
     */
    public ExcursionDTO(){
        
    }

    /**
     * Constructor with ExcursionDTO argument.
     *
     * @param exc ExcursioDTO that provides data.
     */
    public ExcursionDTO(ExcursionDTO exc) {
        id = exc.getId();
        excursionDate = exc.getExcursionDate();
        description = exc.getDescription();
        duration = exc.getDuration();
        destination = exc.getDestination();
        price = exc.getPrice();
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExcursionDate() {
        return excursionDate;
    }

    public void setExcursionDate(Date excursionDate) {
        this.excursionDate = excursionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<TripDTO> getTrips() {
        return trips;
    }

    public void setTrips(Set<TripDTO> trips) {
        this.trips = trips;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.excursionDate);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.duration);
        hash = 29* hash + Objects.hashCode(this.destination);
        hash = 29* hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ExcursionDTO)) {
            return false;
        }
        final ExcursionDTO other = (ExcursionDTO) obj;
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

    @Override
    public String toString() {
        return "ExcursionDTO{" + "id=" + id +
                ", excursionDate=" + excursionDate +
                ", description=" + description +
                ", duration=" + duration +
                ", destination=" + destination +
                ", price=" + price +
                ", trips=" + trips + '}';
    }

    
    
    
}
