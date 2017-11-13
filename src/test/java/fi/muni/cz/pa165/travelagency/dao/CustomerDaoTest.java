/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.cz.pa165.travelagency.dao;

import fi.muni.cz.pa165.travelagency.PersistenceSampleApplicationContext;
import fi.muni.cz.pa165.travelagency.entity.Customer;
import fi.muni.cz.pa165.travelagency.entity.Reservation;
import fi.muni.cz.pa165.travelagency.entity.Trip;
import fi.muni.cz.pa165.travelagency.enums.PaymentStateType;
import java.util.Calendar;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for Customer Dao implementation.
 *
 * @author (name = "Nermin Jukan", UCO = "<473370>")
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoTest extends AbstractTestNGSpringContextTests{

    @Autowired
    public CustomerDao customerDao;
    
    @Autowired
    public ExcursionDao excursionDao;
    
    @Autowired
    public ReservationDao reservationDao;
    
    @Autowired
    public TripDao tripDao;
    
    private Customer customerOne;
    private Customer customerTwo;
    private Reservation reservation;
    private Trip trip;
    private Calendar cal;
    
    
    @BeforeMethod
    public void setupEntities(){
        customerOne = new Customer();
        cal = Calendar.getInstance();
        cal.set(2017, 28, 10);
        customerOne.setBirthDate(cal.getTime());
        customerOne.setEmail("someone@tester.si");
        customerOne.setIdCardNumber("123");
        customerOne.setSurname("Tester");
        
        customerTwo = new Customer();
        customerTwo.setBirthDate(cal.getTime());
        customerTwo.setEmail("someoneNew@testerTwo.cz");
        customerTwo.setIdCardNumber("321");
        customerTwo.setSurname("TesterTwo");
        
        
    }
    
    @Test
    public void testCreate(){
        
        Assert.assertEquals(customerDao.findAll().size(), 0);
        
        customerDao.create(customerOne);
        
        Assert.assertEquals(customerDao.findAll().size(), 1);
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getSurname(), "Tester");
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getEmail(), "someone@tester.si");
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getIdCardNumber(), "123");
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getBirthDate(), cal.getTime());
    }
    
    @Test
    public void testFindAll(){
        
        customerDao.create(customerOne);
        customerDao.create(customerTwo);
        
        Assert.assertEquals(customerDao.findAll().size(), 2);
    }
    
    @Test(expectedExceptions = Exception.class)
    public void testSameCustomerCreate(){
        Customer theDouble = new Customer();
        theDouble.setBirthDate(cal.getTime());
        theDouble.setEmail("someone@tester.si");
        theDouble.setIdCardNumber("123");
        theDouble.setSurname("Tester");
        
        customerDao.create(customerOne);
        customerDao.create(theDouble);
        
        //Assert.assertEquals(customerDao.findAll().size(), 1);
    }
    
    @Test(expectedExceptions=NullPointerException.class)
    public void testNullCustomer(){
        Customer nullBudy = new Customer();
        customerDao.create(nullBudy);
    }
    
    @Test
    public void testFindById(){
        customerDao.create(customerOne);
        customerDao.create(customerTwo);
        
        Customer first = customerDao.findById(customerOne.getId());
        Customer second = customerDao.findById(customerTwo.getId());
        

        Assert.assertNotNull(first);
        Assert.assertEquals(first, customerOne);
        
        Assert.assertNotNull(second);
        Assert.assertEquals(second, customerTwo);
        
    }
    
    @Test(expectedExceptions = org.springframework.dao.EmptyResultDataAccessException.class)
    public void testFindNonExistingId(){
        Customer nullBudy = customerDao.findById(Long.MIN_VALUE);
        Assert.assertNull(nullBudy);
    }
    
    @Test
    public void testRemove(){
        customerDao.create(customerOne);
        customerDao.create(customerTwo);
        
        customerDao.remove(customerOne);
        
        Assert.assertEquals(customerDao.findById(customerTwo.getId()), customerTwo);
        
        customerDao.remove(customerTwo);
        
        Assert.assertTrue(customerDao.findAll().isEmpty());
    }
    
    @Test
    public void testUpdate(){
        customerOne.setPhoneNumber("147");
        customerDao.create(customerOne);
        
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getPhoneNumber(), "147");
        
        customerOne.setPhoneNumber("741");
        customerDao.update(customerOne);
        
        Assert.assertEquals(customerDao.findById(customerOne.getId()).getPhoneNumber(), "741");
    }
    
    @Test
    public void testFindByReservation(){
        customerDao.create(customerOne);
        
        trip = new Trip();
        trip.setName("Somewhere");
        tripDao.create(trip);
        
        reservation = new Reservation();
        reservation.setPaymentState(PaymentStateType.Paid);
        reservation.setCreated(cal.getTime());
        reservation.setCustomer(customerOne);
        reservation.setReservedTrip(trip);
        reservationDao.create(reservation);
        
        
        Customer tester = customerDao.findByReservation(reservation);
        
        Assert.assertNotNull(tester);
        Assert.assertEquals(tester, customerOne);
    }
    
    
}
