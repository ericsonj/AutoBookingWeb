package test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.catalina.ant.ResourcesTask;
import test.containerlist.ListObjects;
import test.containerlist.ListResponseAHour;
import test.containerlist.ListResponseDate;
import test.containerlist.ListService;
import test.dao.AvailableHourDao;
import test.dao.CarDao;
import test.dao.CarDateDao;
import test.dao.ServiceDao;
import test.dao.UserDao;
import test.elements.AvailableHour;
import test.elements.Car;
import test.elements.CarDate;
import test.elements.RequestMessage;
import test.elements.ResponseAvailableHour;
import test.elements.ResponseDate;
import test.elements.ResponseMessage;
import test.elements.Service;
import test.elements.User;
import test.elements.UserBooking;

/**
 *
 * @author ejoseph
 */
@Path("/autobookingserver")
@Produces("application/json")
public class AutoBookingService {

    private UserDao userDao = new UserDao();
    private CarDao carDao = new CarDao();
    private ServiceDao serviceDao = new ServiceDao();
    private CarDateDao carDateDao = new CarDateDao();
    private AvailableHourDao dao = new AvailableHourDao();

    @GET
    @Path("/get")
    @Produces("text/plain")
    public String getA() {
        return "AutoBookingServer";
    }

    @POST
    @Path("/requestMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMessage getRequestDate(RequestMessage requestMessage) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>Me llamaron");
        return new ResponseMessage("OK", true, getService());

    }

    @POST
    @Path("/requestService")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListService getService(RequestMessage requestMessage) {
        ListService listService = new ListService();
        ServiceDao dao = new ServiceDao();
        List<Service> list = dao.findAll();
        LinkedList<Service> services = new LinkedList<>();
        for (Service list1 : list) {
            services.add(list1);
        }
        listService.setList(services);
        return listService;
    }

    @POST
    @Path("/requestBooking")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseMessage setUserBooking(UserBooking userBooking) {
        if (booking(userBooking)) {
            return new ResponseMessage("OK", true, "SUCCESSFUL");
        }
        return new ResponseMessage("ERROR", false, "ERROR");
    }

    @POST
    @Path("/requestDate")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListResponseDate getDates(RequestMessage requestMessage) {
        String login = requestMessage.getMessage();
        ListResponseDate listResponseDate = new ListResponseDate();
        List<CarDate> carDate = carDateDao.findAllByProperty("user_id", login);
        System.out.println(">>>>>>>>>>.." + carDate.size());
        LinkedList<ResponseDate> responseDate = new LinkedList<>();
        for (CarDate carDate1 : carDate) {
            responseDate.addFirst(new ResponseDate(carDate1.getService().getNameService(), carDate1.getCar().getCarCode(), carDate1.getDate(), carDate1.getState()));
        }
        listResponseDate.setList(responseDate);
        return listResponseDate;
    }

    @POST
    @Path("/requestAvailableHour")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListResponseAHour getAvailableHour(RequestMessage requestMessage) {

        String sDate = requestMessage.getMessage();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LinkedList<ResponseAvailableHour> listAvailableHour = new LinkedList<>();
        ListResponseAHour responseAHour = new ListResponseAHour();
        try {
            Date requestDate = dateFormat.parse(sDate);
            Date currentDate = new Date();

            String sResquestDate = dateFormat.format(requestDate);
            String sCurrentDate = dateFormat.format(currentDate);
            if (sResquestDate.equals(sCurrentDate)) {
                System.out.println(">>>>>>>>>>>the seem day   ");
                if (currentDate.after(getDateFinal())) {
                    System.out.println("Ninguna Hora para el mismo dia");
                    responseAHour.setList(listAvailableHour);
                    return responseAHour;
                } else {
                    if (findinDate(requestDate).isEmpty()) {
                        System.out.println("CREAR PULL");
                        dao.createPullDate(requestDate);
                    }
                    responseAHour.setList(findDateNotBookingCurrentDay(requestDate));
                    return responseAHour;
                }
            }else{  
                System.out.println(">>>>>>>>>>>>No the seem");
                if (requestDate.after(currentDate)) {
                    if (findinDate(requestDate).isEmpty()) {
                        System.out.println("CREAR PULL");
                        dao.createPullDate(requestDate);
                    }
                    responseAHour.setList(findDateNotBooking(requestDate));
                    return responseAHour;
                }
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + dateFormat.format(requestDate));

        } catch (ParseException ex) {
            Logger.getLogger(AutoBookingService.class.getName()).log(Level.SEVERE, null, ex);
        }

        responseAHour.setList(listAvailableHour);
        return responseAHour;
    }

    public String getService() {
        ServiceDao dao = new ServiceDao();
        List<Service> list = dao.findAll();
        return list.toString();
    }

    public boolean booking(UserBooking userBooking) {

        User user = userDao.find(userBooking.getName());
        if (user == null) {
            String login = userBooking.getName().replace(" ", "");
            String name = userBooking.getName();
            String email = userBooking.getEmail();
            String document = userBooking.getDocument();
            user = new User(login, "clave", name, email, document, false);
            userDao.create(user);
        }
        Car car = carDao.find(userBooking.getCar_dode());
        if (car == null) {
            car = new Car(userBooking.getCar_dode(), userBooking.getCar_brand());
            carDao.create(car);
        }
        Service service = serviceDao.find(userBooking.getService_id());
        
        System.out.println(">>>>>>>>>>>>>>>>>>      id "+userBooking.getAvailableHourId());
        AvailableHour availableHour = dao.find(userBooking.getAvailableHourId());
        availableHour.setIsBooking("Y");
        dao.edit(availableHour);
        
        CarDate carDate = new CarDate(user, service, car, userBooking.getDate(), "BOOKING");
        try {
            long carDateId = carDateDao.create(carDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Date getDateFinal() {
        Date date = new Date();
        date.setHours(18);
        date.setMinutes(0);
        date.setSeconds(1);
        return date;
    }

    public LinkedList<ResponseAvailableHour> findBetweenDate(Date startDate, Date enddate) {
        LinkedList<ResponseAvailableHour> resp = new LinkedList<>();
        List<AvailableHour> list = dao.findBetweenDate(startDate, enddate);
        for (AvailableHour list1 : list) {
            resp.add(new ResponseAvailableHour(list1.getId(), list1.getDateHour()));
        }
        return resp;
    }

    public LinkedList<ResponseAvailableHour> findinDate(Date date) {
        LinkedList<ResponseAvailableHour> resp = new LinkedList<>();
        List<AvailableHour> list = dao.findInDate(date);
        for (AvailableHour list1 : list) {
            resp.add(new ResponseAvailableHour(list1.getId(), list1.getDateHour()));
        }
        return resp;
    }

    public LinkedList<ResponseAvailableHour> findDateNotBooking(Date date) {
        LinkedList<ResponseAvailableHour> resp = new LinkedList<>();
        List<AvailableHour> list = dao.findInDateNotBooking(date);
        for (AvailableHour list1 : list) {
            System.out.println(">><<<<<<<<<   list1 "+list1.getId());
            resp.add(new ResponseAvailableHour(list1.getId(), list1.getDateHour()));
        }
        return resp;
    }
    
    public LinkedList<ResponseAvailableHour> findDateNotBookingCurrentDay(Date date) {
        LinkedList<ResponseAvailableHour> resp = new LinkedList<>();
        List<AvailableHour> list = dao.findInDateNotBookingCurrentDay(date);
        for (AvailableHour list1 : list) {
            resp.add(new ResponseAvailableHour(list1.getId(), list1.getDateHour()));
        }
        return resp;
    }

}
