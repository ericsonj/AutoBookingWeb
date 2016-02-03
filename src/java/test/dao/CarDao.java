package test.dao;

import test.elements.Car;
import test.hibernate.EhElementLogicFacade;


/**
 *
 * @author ejoseph
 */
public class CarDao extends EhElementLogicFacade<Car, String>{

    @Override
    public Class<Car> getElementClass() {
        return Car.class;
    }
}
