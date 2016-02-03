package test.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import test.elements.CarDate;
import test.hibernate.EhElementLogicFacade;

/**
 *
 * @author ejoseph
 */
public class CarDateDao extends EhElementLogicFacade<CarDate, Long>{

    @Override
    public Class<CarDate> getElementClass() {
        return CarDate.class; 
    }
    
    public List<CarDate> findByDateLast(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CarDate> list = findByQuery("SELECT R FROM "+CarDate.class.getCanonicalName()+" R WHERE date < '"+dateFormat.format(date)+"' AND state = 'BOOKING' ");
        return list;
    }
    
}
