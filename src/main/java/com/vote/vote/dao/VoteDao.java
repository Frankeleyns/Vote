package com.vote.vote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.vote.vote.domain.Organize;
import com.vote.vote.domain.User;
import com.vote.vote.domain.Vote;

@Component
@Transactional
public class VoteDao extends BaseDao{
	
	public void save(Vote vote){
		getsession().save(vote);
	}
	
	public Vote findUserAndOrg(User user,Organize organize){
		DetachedCriteria dc = DetachedCriteria.forClass(Vote.class);
		dc.add(Property.forName("user").eq(user));
		dc.add(Property.forName("organize").eq(organize));
		Criteria criteria = dc.getExecutableCriteria(getsession());
		List<Vote> list = criteria.list();
		if(list.size() > 0 && list != null){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<Vote> findVoteByUserid(User user){
		DetachedCriteria dc = DetachedCriteria.forClass(Vote.class);
		dc.add(Property.forName("user").eq(user));		
		Criteria criteria = dc.getExecutableCriteria(getsession());
		List<Vote> list = criteria.list();
		return list;
	}
	
	
	public Object findVoteByOrganize(Organize organize){
		String sql = "select sum(fraction) as 'total' from vote where organize=:organize";
		Query<?> query = getsession().createNativeQuery(sql).setParameter("organize", organize);
		Object object = query.getSingleResult();
		return object;
	}

}
