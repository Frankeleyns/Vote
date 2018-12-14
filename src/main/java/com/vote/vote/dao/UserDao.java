package com.vote.vote.dao;

import com.vote.vote.domain.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

@Component
@Transactional
public class UserDao extends BaseDao{

    public void save(User user){
        getsession().save(user);
    }

    public User findById(String id){
        User user = getsession().get(User.class, id);
        return user;
    }
    
    public List findAll(){
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
    	Criteria criteria = detachedCriteria.getExecutableCriteria(getsession());
    	List list = criteria.list();
    	return list;
    }

}
