package com.vote.vote.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vote.vote.domain.Organize;
import com.vote.vote.service.OrganizeService;
import com.vote.vote.service.VoteService;

@RestController
public class OrganizeController {
	
	@Autowired
	private OrganizeService orgService;
	@Autowired
	private VoteService voteService;
	
	@GetMapping("/orgVote")
	public List<Organize> findOrganize(String userid){
		List<Organize> list = orgService.findOrganize(userid);
		return list;
	}
	
	@GetMapping("/total")
	@ResponseBody
	public Map<String, Object> total(){
		Map<String,Object> map  = new LinkedHashMap<String,Object>();
		List<Organize> list = orgService.findTotal();
		for (Organize organize : list) {
			System.out.println("==="+organize.getId());
			Object total = voteService.findVoteTotal(organize);
			map.put(organize.getName(), total);
			System.out.println(map);
		}
		return map;
	}

}
