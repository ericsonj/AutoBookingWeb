package test.dao;


import test.elements.Service;
import test.hibernate.EhElementLogicFacade;


/**
 *
 * @author ejoseph
 */
public class ServiceDao extends EhElementLogicFacade<Service, Long>{

    @Override
    public Class<Service> getElementClass() {
        return Service.class;
    }     
}
