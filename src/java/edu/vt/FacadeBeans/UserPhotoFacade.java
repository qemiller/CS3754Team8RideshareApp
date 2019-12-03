/*
 * Created by Justin Kennedy on 2019.12.01  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.UserPhoto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jusmk96
 */
@Stateless
public class UserPhotoFacade extends AbstractFacade<UserPhoto> {

    @PersistenceContext(unitName = "VTRides-Team7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserPhotoFacade() {
        super(UserPhoto.class);
    }
    
    /*
    *********************************************************
    The following method is added to the auto generated code.
    *********************************************************
     */
    /**
     * @param primaryKey is the Primary Key of the User entity in a table row in the database.
     * @return a list of photos associated with the User whose primary key is primaryKey
     */
    public List<UserPhoto> findPhotosByUserPrimaryKey(Integer primaryKey) {

        return (List<UserPhoto>) em.createNamedQuery("UserPhoto.findPhotosByUserDatabasePrimaryKey")
                .setParameter("primaryKey", primaryKey)
                .getResultList();
    }

    /* The following methods are inherited from the parent AbstractFacade class:
    
        create
        edit
        find
        findAll
        remove
     */
}
