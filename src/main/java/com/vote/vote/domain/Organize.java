package com.vote.vote.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@FilterDef(name = "vote",parameters = {@ParamDef(name = "userid" , type = "string")})
public class Organize implements Serializable{
	
	@Id
	private String id;

	private String name;  //小组名
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "organize")
	@Filter(name = "vote",condition = "userid=:userid")
	private Set<Vote> votes = new HashSet<Vote>(0);
	
	@Transient
	private Vote myvote;
	
	public Vote getMyvote(){
		try {
			this.myvote = getVotes().iterator().next();
		} catch(Exception e) {
		}
		return this.myvote;
	}
}
