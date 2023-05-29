package com.ftn.sbnz.service;
import com.ftn.sbnz.model.Hero;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	private KieSession session;

	@PostMapping("/test")
	public Hero test(@RequestBody Hero hero) {
		session.insert(hero);
		session.fireAllRules();
		return hero;
	}
	
	@PostMapping("/check")
	public void test(@RequestParam String id) {
		session.fireAllRules();
	}
}
