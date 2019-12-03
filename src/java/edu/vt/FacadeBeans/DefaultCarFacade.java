/*
 * Created by Justin Kennedy on 2019.12.02  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.DefaultCar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jusmk96
 */
@Stateless
public class DefaultCarFacade extends AbstractFacade<DefaultCar> {

    @PersistenceContext(unitName = "VTRides-Team7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DefaultCarFacade() {
        super(DefaultCar.class);
    }
    
    /**
     *
     * @param key
     * @return a list of object references of publicVideos with the input parameter category
     */
    public List<DefaultCar> findByUserPrimaryKey(Integer key) {
        // the % ____ % means that anything with a categroy containing the substring of categroy 
        //will be placed in the list of videos
        List<DefaultCar> cars = em.createNamedQuery("DefaultCar.findByUserPrimaryKey")
                .setParameter("primaryKey", key)
                .getResultList();

        return cars;
    }
}
