package fi.muni.cz.pa165.travelagency.facade;

import fi.muni.cz.pa165.travelagency.dto.ExcursionDTO;
import fi.muni.cz.pa165.travelagency.dto.TripCreateDTO;
import fi.muni.cz.pa165.travelagency.dto.TripDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Pavel Kotala, 437164
 */
public interface TripFacade {


    /**
     * Adds given trip to database.
     * @param trip trip to be added
     * @return id of added trip
     */
    Long createTrip(TripCreateDTO trip);

    /**
     * Adds given excursion to given trip.
     * @param tripId id of trip to add excursion to
     * @param excursionId id of excursion to be added to trip
     */
    void addExcursion(Long tripId, Long excursionId);

    /**
     * Adds all given excursions to given trip.
     * @param tripId id of trip to add excursions to
     * @param excursionIds ids of excursions to be added to trip
     */
    void addAllExcursions(Long tripId, Set<Long> excursionIds);

    /**
     * Removes given excursion from given trip.
     * @param tripId id of trip to remove excursion from
     * @param excursionId id of excursion to be removed from trip
     */
    void removeExcursion(Long tripId, Long excursionId);
    
    /**
     * Updates given trip
     * @param trip trip to be updated
     */
    void updateTrip(TripDTO trip);

    /**
     * Deletes given trip.
     * @param tripId id of trip to be deleted
     */
    void deleteTrip(Long tripId);

    /**
     * Returns all trips.
     * @return all trips
     */
    List<TripDTO> getAllTrips();

    /**
     * Returns all trips with given name.
     * @param tripName name of the trips
     * @return all trips with given name
     */
    List<TripDTO> getTripsByName(String tripName);

    /**
     * Returns all trips with given destination.
     * @param destination destination of the trip
     * @return all trips with given destination
     */
    List<TripDTO> getTripsByDestination(String destination);

    /**
     * Returns all trips between given dates.
     * @param start start date
     * @param end end date
     * @return all trips between given dates.
     */
    List<TripDTO> getTripsBetween(Date start, Date end);

    /**
     * Returns trip with given id.
     * @param id id of desired trip
     * @return trip with given id
     */
    TripDTO getTripWithId(Long id);

    /**
     * Returns all suitable excursions for given trip.
     * Suitable excursion has the same destination as given trip and
     * date of excursion is between start and end dates of given trip
     * @param tripId id of trip to find suitable excursions for
     * @return all suitable excursions
     */
    List<ExcursionDTO> getAllSuitableExcursions(Long tripId);

    /**
     * Returns next n trips in the sequence after this trip.
     * The trip sequence is sorted lexicographically by start date, end date and name, all in ascending order.
     * Trips that take place later than one month from the start of given trip will be disregarded.
     * @param tripId id of trip to find trips after
     * @param n number of trips to return, positive integer
     * @return next 10 trips
     */
    List<TripDTO> getNextTrips(Long tripId, int n);

    /**
     * Refreshes trips excursions to be the all current suitable excursions
     * @param tripId id of trip to refresh excursions
     */
    void refreshExcursions(Long tripId);
}
