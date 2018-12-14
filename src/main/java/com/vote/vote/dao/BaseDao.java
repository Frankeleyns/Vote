package com.vote.vote.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public class BaseDao {
	
	
    @PersistenceContext
    private EntityManager entityManager;

    protected Session getsession(){
        return entityManager.unwrap(Session.class);
    }

}
