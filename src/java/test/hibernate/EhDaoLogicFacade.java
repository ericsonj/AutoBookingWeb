package test.hibernate;

import test.*;
import java.io.Serializable;
import org.hibernate.HibernateException;

/**
 *
 * @author ejoseph
 * @param <E>
 * @param <PK>
 */
public interface EhDaoLogicFacade<E, PK extends Serializable>{
    
    PK create(E Entity) throws HibernateException;
    
    void edit(E Entity) throws HibernateException;
    
    E find (PK id) throws HibernateException;
    
    void remove(E Entity) throws HibernateException;
     
}
