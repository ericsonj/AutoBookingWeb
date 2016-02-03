package test.hibernate;

import test.*;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ejoseph
 * @param <E>
 * @param <PK>
 */
public abstract class EhElementLogicFacade<E, PK extends Serializable> implements EhDaoLogicFacade<E, PK> {

    protected Session session;
    protected Transaction tx;

    public abstract Class<E> getElementClass();
    
    private void initOperation() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Override
    public PK create(E Entity) throws HibernateException {

        PK entityId = null;

        try {
            this.initOperation();
            tx = session.beginTransaction();
            entityId = (PK) session.save(Entity);
            tx.commit();
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }
        return entityId;
    }

    @Override
    public void edit(E Entity) throws HibernateException {
        try {
            this.initOperation();
            tx = session.beginTransaction();
            session.update(Entity);
            tx.commit();
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public E find(PK id) throws HibernateException {
        try {
            this.initOperation();
            tx = session.beginTransaction();
            E result = (E)session.get(getElementClass(), id);
            tx.commit();
            return result;
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }      
    }

    @Override
    public void remove(E Entity) throws HibernateException {
        try {
            this.initOperation();
            tx = session.beginTransaction();
            session.delete(Entity);
            tx.commit();
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        } 
    }
    
    public List<E> findAll(){
        try {
            this.initOperation();
            tx = session.beginTransaction();
            List<E> entitys = session.createQuery("SELECT R FROM "+getElementClass().getCanonicalName()+" R").list();
            tx.commit();
            return entitys;
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }
    
    public List<E> findByQuery(String QueryHQL){
        try {
            this.initOperation();
            tx = session.beginTransaction();
            List<E> entitys = session.createQuery(QueryHQL).list();
            tx.commit();
            return entitys;
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }
    
    
    public List<E> findAllByProperty(String property, Object value){
        try {
            this.initOperation();
            tx = session.beginTransaction();
            
            Query query = session.createQuery("FROM "+getElementClass().getCanonicalName()+" WHERE "+property+" = :value");
            query.setParameter("value", value);
            List<E> entitys = query.list();
            tx.commit();
            return entitys;
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new HibernateException(e);
        } finally {
            session.close();
        }
    }

}
