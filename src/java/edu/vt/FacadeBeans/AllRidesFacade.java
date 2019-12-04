/*
 * Created by Justin Kennedy on 2019.12.01  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.AllRides;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jusmk96
 */
@Stateless
public class AllRidesFacade extends AbstractFacade<AllRides> {

    @PersistenceContext(unitName = "VTRides-Team7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AllRidesFacade() {
        super(AllRides.class);
    }
    
    public AllRides getAllRides(int id){
        return em.find(AllRides.class, id);
    }
    
    /**
     *
     * @param start
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<AllRides> findByStartingCity(String start) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        start = "%" + start + "%";
        List<AllRides> rides = em.createNamedQuery("AllRides.findByStartingCity")
                .setParameter("startingCity", start)
                .getResultList();

        return rides;
    }
    
    /**
     *
     * @param end
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<AllRides> findByEndingCity(String end) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        end = "%" + end + "%";
        List<AllRides> rides = em.createNamedQuery("AllRides.findByEndingCity")
                .setParameter("endingCity", end)
                .getResultList();

        return rides;
    }
    
    /**
     *
     * @param key
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<AllRides> findByUserPrimaryKey(Integer key) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        List<AllRides> rides = em.createNamedQuery("AllRides.findByUserPrimaryKey")
                .setParameter("primaryKey", key)
                .getResultList();

        return rides;
    }
}
