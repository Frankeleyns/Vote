package com.vote.vote.web;

import com.vote.vote.dao.UserDao;
import com.vote.vote.domain.User;
import com.vote.vote.service.UserService;
import com.vote.vote.utils.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserDao userDao;
    
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<User> auth(String id){
        log.error(id);
        User user = userService.login(id); 
        return ResponseEntity.ok(user);
    }
    
}
