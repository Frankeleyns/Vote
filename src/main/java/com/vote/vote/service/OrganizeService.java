package com.vote.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.vote.dao.OrganizeDao;
import com.vote.vote.dao.UserDao;
import com.vote.vote.domain.Organize;
import com.vote.vote.domain.User;

@Service
public class OrganizeService {
	
	@Autowired
	private OrganizeDao orgDao;
	@Autowired
	private UserDao userDao;
	
	public List<Organize> findOrganize(String userid){
		User user = userDao.findById(userid);
		List<Organize> list = orgDao.findOrganize(user);
		return list;
	}
	
	public List<Organize> findTotal(){
		List<Organize> list = orgDao.findAllOrganize();
		return list;
	}

}
