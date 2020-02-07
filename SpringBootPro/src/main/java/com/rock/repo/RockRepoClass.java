package com.rock.repo;

import org.springframework.stereotype.Service;

@Service
public class RockRepoClass implements RockRepo {

	@Override
	public String something() {
		return "interface autowired";
	}

}
