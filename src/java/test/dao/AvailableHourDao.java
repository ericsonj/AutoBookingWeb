package test.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import test.elements.AvailableHour;
import test.hibernate.EhElementLogicFacade;

/**
 *
 * @author ejoseph
 */
public class AvailableHourDao extends EhElementLogicFacade<AvailableHour, Long>{

    
    private SimpleDateFormat dateHourFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    
    @Override
    public Class<AvailableHour> getElementClass() {
        return AvailableHour.class;
    }
    
    public List<AvailableHour> findBetweenDate(Date startDate , Date endDate){
        
        return findByQuery("SELECT R FROM "+ getElementClass().getCanonicalName() 
                + " R WHERE ( date BETWEEN '"+dateHourFormat.format(startDate)+"' AND '"+dateHourFormat.format(endDate)+"') ");
    }
    
    public void createPullDate(Date date){
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        for (int i = 8; i <= 18; i++) {
            date.setHours(i);
            create(new AvailableHour(date, "N"));
        }
        
    }
    
    public List<AvailableHour> findInDate(Date date){
        return findByQuery("SELECT R FROM "+ getElementClass().getCanonicalName() 
                + " R WHERE date LIKE '"+dateFormat.format(date)+" %'");
    }
    
    public List<AvailableHour> findInDateNotBooking(Date date){
        return findByQuery("SELECT R FROM "+ getElementClass().getCanonicalName() 
                + " R WHERE date LIKE '"+dateFormat.format(date)+" %' AND isBooking = 'N'");
    }
    
    public List<AvailableHour> findInDateNotBookingCurrentDay(Date date){
        Date finishDate = new Date(date.getTime());
        finishDate.setHours(23);
        finishDate.setMinutes(59);
        finishDate.setSeconds(59);
        return findByQuery("SELECT R FROM "+ getElementClass().getCanonicalName() 
                + " R WHERE ( date BETWEEN '"+dateHourFormat.format(date)+"' AND '"+dateHourFormat.format(finishDate)+"' ) AND isBooking = 'N'");
    }
}
