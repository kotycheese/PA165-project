package fi.muni.cz.pa165.travelagency.rest.controllers;

import fi.muni.cz.pa165.travelagency.dto.TripCreateDTO;
import fi.muni.cz.pa165.travelagency.dto.TripDTO;
import fi.muni.cz.pa165.travelagency.facade.TripFacade;
import fi.muni.cz.pa165.travelagency.rest.ApiUris;
import fi.muni.cz.pa165.travelagency.rest.exceptions.ResourceAlreadyExistingException;
import fi.muni.cz.pa165.travelagency.rest.exceptions.ResourceNotFoundException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for Trip
 * 
 * @author Radoslav Micko <445611>
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TRIPS)
public class TripsController {
    
    static final Logger LOGGER = LoggerFactory.getLogger(TripsController.class);

    @Autowired
    private TripFacade tripFacade;
    
    /**
     * Get list of all Trips
     * 
     * @return List of TripDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<TripDTO> getTrips() {
        LOGGER.debug("REST getTrips()");
        return tripFacade.getAllTrips();
    }
    
    /**
     *
     * Get trips by identifier id 
     *
     * @param id identifier for a trip
     * @return TripDTO
     * @throws ResourceNotFoundException If there is no such trip
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TripDTO getTrip(@PathVariable("id") long id) throws ResourceNotFoundException {
        LOGGER.debug("REST getTrip({})", id);
        TripDTO tripDTO = tripFacade.getTripWithId(id);
        if (tripDTO != null) {
            return tripDTO;
        } else {
            throw new ResourceNotFoundException("Can't delete trip that is reserved.");
        }
    }
    
    /**
     * Delete one trip by id 
     *
     * @param id identifier for trip
     * @throws ResourceNotFoundException If there is no such trip
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteTrip(@PathVariable("id") long id) throws ResourceNotFoundException {
        LOGGER.debug("rest deleteProduct({})", id);
        try {
            tripFacade.deleteTrip(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
    
     /**
     * Create a new trip by POST method
     * 
     * @param trip TripDTO with required fields for creation
     * @return the created trip TripDTO
     * @throws ResourceAlreadyExistingException If trip already exists
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TripDTO createTrip(@RequestBody TripCreateDTO trip) throws ResourceAlreadyExistingException {
        LOGGER.debug("REST createTrip()");
        try {
            Long id = tripFacade.createTrip(trip);
            return tripFacade.getTripWithId(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }
    
    /**
     * Update the price for one trip by PUT method
     *
     * @param id identified of the trip to be updated
     * @param trip TripDTO with updated fields
     * @return the updated trip TripDTO
     * @throws ResourceNotFoundException If there is no such trip
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TripDTO updateTrip(@PathVariable("id") long id, @RequestBody TripDTO trip) 
            throws ResourceNotFoundException {
        LOGGER.debug("REST updateTrip({})", id);
        try {
            tripFacade.updateTrip(trip);
            return tripFacade.getTripWithId(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }  
}
