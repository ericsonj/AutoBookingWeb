package test.elements;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ejoseph
 */
@Entity
@Table(name = "available_hour")
public class AvailableHour {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "date")
    private Date dateHour;
    
    @Column(name = "isBooking")
    private String isBooking;

    public AvailableHour() {
    }

    public AvailableHour( Date dateHour, String isBooking) {
        this.dateHour = dateHour;
        this.isBooking = isBooking;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateHour() {
        return dateHour;
    }

    public void setDateHour(Date dateHour) {
        this.dateHour = dateHour;
    }

    public String getIsBooking() {
        return isBooking;
    }

    public void setIsBooking(String isBooking) {
        this.isBooking = isBooking;
    }
    
    @Override
    public String toString() {
        return "AvailableHour{" + "id=" + id + ", dateHour=" + dateHour + '}';
    }
  
}
