package com.rock.repo;

import org.springframework.stereotype.Component;

@Component(value = "repo-class-1")
public class RockRepoClass implements RockRepo {

	@Override
	public String something() {
		return "interface autowired - RockRepoClass";
	}

}
		