package com.vote.vote.service;

import com.vote.vote.dao.UserDao;
import com.vote.vote.domain.User;
import com.vote.vote.utils.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User login(String id){
        User user = userDao.findById(id);
        if(user != null){
            return user;
        }else{
            throw new ServiceException("user.notexit");
        }
    }
}
