package com.rock.repo;

import org.springframework.stereotype.Component;

@Component(value = "repo-class-2")	
public class RockRepoClass2 implements RockRepo {

	@Override
	public String something() {
		return "interface autowired - RockRepoClass2";
	}

}
