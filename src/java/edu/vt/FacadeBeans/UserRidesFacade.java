/*
 * Created by Justin Kennedy on 2019.12.01  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.UserRides;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jusmk96
 */
@Stateless
public class UserRidesFacade extends AbstractFacade<UserRides> {

    @PersistenceContext(unitName = "VTRides-Team7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRidesFacade() {
        super(UserRides.class);
    }
    
    public UserRides getUserRides(int id){
        return em.find(UserRides.class, id);
    }
    
    /**
     *
     * @param key
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<UserRides> findByUserPrimaryKey(Integer key) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        List<UserRides> rides = em.createNamedQuery("UserRides.findByUserPrimaryKey")
                .setParameter("primaryKey", key)
                .getResultList();

        return rides;
    }
    
    /**
     *
     * @param id
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<UserRides> findByAllRidesId(Integer id) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        List<UserRides> rides = em.createNamedQuery("UserRides.findByAllRidesId")
                .setParameter("allRidesId", id)
                .getResultList();

        return rides;
    }
}
