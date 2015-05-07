package ua.com.tracksee.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by byte on 4/21/15.
 */
public abstract class GenericDao<T, PK extends Serializable> {


    public void create(T ob){
        getEM().persist(ob);
    }

    public T read(PK id){
        return getEM().find(getType(), id);
    }

    public void update(T ob){
        getEM().merge(ob);
    }


    public void delete(T ob){
        getEM().remove(ob);
    }


    protected abstract EntityManager getEM();


    public abstract Class<T> getType();

}
