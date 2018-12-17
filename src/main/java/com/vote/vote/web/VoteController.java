package com.vote.vote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vote.vote.service.VoteService;

@Controller
public class VoteController {

	@Autowired
	private VoteService voteService;
	
	@PostMapping("/vote")
	@ResponseBody
	public ResponseEntity<Void> vote(@RequestParam String userid,@RequestParam String orgid,@RequestParam int fraction,@RequestParam String ps){
		voteService.vote(userid, orgid, fraction, ps);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/cancel")
	@ResponseBody
	public ResponseEntity<Void> cancel(@RequestParam String userid,@RequestParam String orgid){
		voteService.Cancel(userid, orgid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/clear")
	@ResponseBody
	public ResponseEntity<Void> clear(@RequestParam String userid){
		voteService.Clear(userid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
