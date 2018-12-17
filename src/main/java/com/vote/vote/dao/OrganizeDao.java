package com.vote.vote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Component;

import com.vote.vote.domain.Organize;
import com.vote.vote.domain.User;

@Component
@Transactional
public class OrganizeDao extends BaseDao{
	
	public Organize findByid(String id){
		Organize organize = getsession().get(Organize.class, id);
		return organize;
	}
	
	public List<Organize> findOrganize(User user){
		DetachedCriteria dc = DetachedCriteria.forClass(Organize.class);
		Filter filter = getsession().enableFilter("vote");
		filter.setParameter("userid", user.getId());
		//dc.createAlias("votes", "vote");
		//dc.add(Property.forName("vote.user").eq(user));
		Criteria criteria = dc.getExecutableCriteria(getsession());
		List<Organize> list = criteria.list();
		return list;
	}
	
	
	public List<Organize> findAllOrganize(){
		DetachedCriteria dc = DetachedCriteria.forClass(Organize.class);
		dc.addOrder(Order.asc("id"));
		Criteria criteria = dc.getExecutableCriteria(getsession());
		return criteria.list();
	}

}
