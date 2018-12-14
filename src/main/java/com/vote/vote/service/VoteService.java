package com.vote.vote.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.vote.dao.OrganizeDao;
import com.vote.vote.dao.UserDao;
import com.vote.vote.dao.VoteDao;
import com.vote.vote.domain.Organize;
import com.vote.vote.domain.User;
import com.vote.vote.domain.Vote;
import com.vote.vote.utils.ServiceException;

@Service
public class VoteService {
	
	@Autowired
	private VoteDao voteDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrganizeDao orgDao;
	
	public void vote(String userid,String orgId,int fraction,String ps){
		User user = userDao.findById(userid);
		Organize organize = orgDao.findByid(orgId);
		Vote vote2 = voteDao.findUserAndOrg(user,organize);
		if(vote2 != null){
			throw new ServiceException("vote.exit");
		}
		Vote vote = new Vote();
		vote.setId(UUID.randomUUID().toString().replace("-", ""));
		vote.setUser(user);
		vote.setOrganize(organize);
		vote.setFraction(fraction);
		vote.setPs(ps);
		voteDao.save(vote);
	}
	
	public Object findVoteTotal(Organize organize){
		Object object = voteDao.findVoteByOrganize(organize);
		return object;
	}
	

}
