package fi.muni.cz.pa165.travelagency.service.facade;

import fi.muni.cz.pa165.travelagency.dto.ExcursionDTO;
import fi.muni.cz.pa165.travelagency.dto.TripDTO;
import fi.muni.cz.pa165.travelagency.entity.Excursion;
import fi.muni.cz.pa165.travelagency.entity.Trip;
import fi.muni.cz.pa165.travelagency.facade.ExcursionFacade;
import fi.muni.cz.pa165.travelagency.service.BeanMappingService;
import fi.muni.cz.pa165.travelagency.service.ExcursionService;
import fi.muni.cz.pa165.travelagency.service.TripService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Excursion Facade Implementation for the Travel Angecy project.
 * 
 * @author (name = "Nermin Jukan", UCO = "<473370>")
 */
@Service
@Transactional
public class ExcursionFacadeImpl implements ExcursionFacade{
    
    @Autowired
    private ExcursionService excursionService;
    
    @Autowired
    private TripService tripService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public Long create(ExcursionDTO excursionDTO) {
        
        if (excursionDTO == null) {
            throw new IllegalArgumentException();
        }
        
        Excursion excursion = beanMappingService.mapTo(excursionDTO, Excursion.class);
        
        if (excursion == null) {
            throw new IllegalArgumentException();
        }
        
        excursionService.create(excursion);
        return excursion.getId();
        
    }

    @Override
    public List<ExcursionDTO> getAllExcursions() {
        return beanMappingService.mapTo(excursionService.getAllExcursions(), ExcursionDTO.class);
    }

    @Override
    public ExcursionDTO getByID(Long excursionId) {
        Excursion excursion = excursionService.findById(excursionId);
        return excursion == null ? null : beanMappingService.mapTo(excursion, ExcursionDTO.class);
    }

    @Override
    public void deleteExcursion(ExcursionDTO excursionDTO) {
        Excursion excursion = beanMappingService.mapTo(excursionDTO, Excursion.class);
        
        if (excursion == null) {
            throw new IllegalArgumentException();
        }
        
        Set<TripDTO> removeFromTrips = excursionDTO.getTrips();
        
        if(!removeFromTrips.isEmpty()){
            for(TripDTO tripRemoveFrom : removeFromTrips){
                Trip trip = beanMappingService.mapTo(tripRemoveFrom, Trip.class);
                tripService.removeExcursion(trip, excursion);
            }
        }
        

        excursionService.deleteExcursion(excursion);
    }

    @Override
    public void updateExcursion(ExcursionDTO excursionUpdate) {
        Excursion excursion = beanMappingService.mapTo(excursionUpdate, Excursion.class);

        if (excursion == null) {
            throw new IllegalArgumentException();
        }

        excursionService.update(excursion);
    }

    @Override
    public List<ExcursionDTO> findByPriceLowerThanOrEqual(Integer price) {
        return beanMappingService.mapTo(excursionService.
                findByPriceLowerThanOrEqual(price), ExcursionDTO.class);
    }

    @Override
    public List<ExcursionDTO> findByPriceHigherThanOrEqual(Integer price) {
        return beanMappingService.mapTo(excursionService.
                findByPriceHigherThanOrEqual(price), ExcursionDTO.class);
    }

    @Override
    public List<ExcursionDTO> findByDuration(Integer duration) {
        return beanMappingService.mapTo(excursionService.
                findByDuration(duration), ExcursionDTO.class);
    }
    
}
