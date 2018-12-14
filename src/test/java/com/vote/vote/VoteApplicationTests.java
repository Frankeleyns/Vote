package com.vote.vote;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vote.vote.dao.OrganizeDao;
import com.vote.vote.dao.UserDao;
import com.vote.vote.dao.VoteDao;
import com.vote.vote.domain.Organize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteApplicationTests {

	@Autowired
	UserDao userDao;
	@Autowired
	VoteDao voteDao;
	
	@Autowired
	OrganizeDao orgDao;
	
	@Test
	public void contextLoads() {
		Organize organize = orgDao.findByid("1a");
		Object total = voteDao.findVoteByOrganize(organize);
		System.out.println("==="+total);
	}

}
