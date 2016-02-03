package test.elements;

import java.util.Date;

/**
 *
 * @author ejoseph
 */
public class ResponseAvailableHour {
    
    private long id;
    private Date datehour;

    public ResponseAvailableHour() {
    }

    public ResponseAvailableHour(long id, Date datehour) {
        this.id = id;
        this.datehour = datehour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatehour() {
        return datehour;
    }

    public void setDatehour(Date datehour) {
        this.datehour = datehour;
    }

    @Override
    public String toString() {
        return "ResponseAvailableHour{" + "id=" + id + ", datehour=" + datehour + '}';
    }
   
}
