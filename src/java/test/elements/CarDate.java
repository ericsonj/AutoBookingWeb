package test.elements;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ejoseph
 */
@Entity
@Table(name = "car_date")
public class CarDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_date_id")
    private long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service service;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "car_code")
    private Car car;

    @Column(name = "date")
    private Date date;

    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "state")
    private String state;

    public CarDate() {
    }

    public CarDate(User user, Service service, Car car, Date date, Date createdDate, String state) {
        this.user = user;
        this.service = service;
        this.car = car;
        this.date = date;
        this.createdDate = createdDate;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CarDate{" + "id=" + id + ", user=" + user + ", service=" + service + ", car=" + car + ", date=" + date + ", createdDate=" + createdDate + ", state=" + state + '}';
    }
   
}
