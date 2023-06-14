package com.ftn.sbnz.service;
import com.ftn.sbnz.model.Hero;
import com.ftn.sbnz.model.RequestPayload;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
//	@Autowired
//	private KieSession session;
	private List<Hero> recommendedHeroes;
	private List<Hero> myTeamResponse;
	private List<Hero> oppositeTeamResponse;

	@GetMapping("/getAllHeroNames")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<String> getAllHeroNames() {
		List<String> heroNames = new ArrayList<String>();
		
		List<Hero> heroes = HeroFactory.getAllHeroes();
		
		for (Hero hero : heroes) {
			heroNames.add(hero.getLocalized_name());
		}
		
		return heroNames;
	}
	
	@PostMapping(value = "/sendRequest")
	@CrossOrigin(origins = "http://localhost:4200")
    public void sendRequest(@RequestBody RequestPayload requestPayload) {
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession session = kc.newKieSession("session");

        String rank = requestPayload.getRank();
        session.setGlobal("player_rank", rank);
        
        String[] myTeamFront = requestPayload.getMyTeam();
        List<Hero> myTeam = fillTeamListWithHeroObjectsFromNames(myTeamFront);
        session.insert(myTeam);
        session.setGlobal("myTeam", myTeam);
        setMyTeamResponse(myTeam);
        
        String[] oppositeTeamFront = requestPayload.getOppositeTeam();
        List<Hero> oppositeTeam = fillTeamListWithHeroObjectsFromNames(oppositeTeamFront);
        session.setGlobal("oppositeTeam", oppositeTeam);
        setOppositeTeamResponse(oppositeTeam);
        
        List<String> pickedHeroes = fillPickedHeroes(myTeam, oppositeTeam);
        session.setGlobal("pickedHeroes", pickedHeroes);
        
        List<String> counterPicks = fillCounterPicks(oppositeTeam);
        session.setGlobal("counterPicks", counterPicks);
        
        List<String> combos = fillCombos(myTeam);
        session.setGlobal("combos", combos);
        
        if (((List<Hero>) session.getGlobal("recommendedHeroes")).isEmpty()) {
            session.fireAllRules();
            setRecommendedHeroes(session.getGlobal("recommendedHeroes"));
            
        }
        else {
        	List<Hero> emptyList = new ArrayList<Hero>();
        	setRecommendedHeroes(emptyList);
        	session.setGlobal("recommendedHeroes", emptyList);
        	session.fireAllRules();
        }
    }
	
	@GetMapping("/getMyTeam")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Hero> getMyTeam() {
		return getMyTeamResponse();
	}
	
	@GetMapping("/getOppositeTeam")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Hero> getOppositeTeam() {
		return getOppositeTeamResponse();
	}
	
	@GetMapping("/getResult")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Hero> getReslut() {
		return getRecommendedHeroes();
	}
	
	
	private List<String> fillCombos(List<Hero> myTeam) {
		List<String> combos = new ArrayList<>();
        for (Hero hero : myTeam) {
        	for (String heroLocalizedName: hero.getCombo()) {
        		combos.add(heroLocalizedName);
        		System.out.println(heroLocalizedName);
        	}
        }
		return combos;
	}

	private List<String> fillCounterPicks(List<Hero> oppositeTeam) {
		List<String> counterPicks = new ArrayList<>();
        for (Hero hero : oppositeTeam) {
        	for (String heroLocalizedName : hero.getBadAgainst()) {
        		counterPicks.add(heroLocalizedName);
        	}
        }
		return counterPicks;
	}

	private List<String> fillPickedHeroes(List<Hero> myTeam, List<Hero> oppositeTeam) {
		List<String> pickedHeroes = new ArrayList<>();
        for (Hero hero : oppositeTeam) {
			pickedHeroes.add(hero.getLocalized_name());
		}
        for (Hero hero : myTeam) {
			pickedHeroes.add(hero.getLocalized_name());
		}
		return pickedHeroes;
	}

	private List<Hero> fillTeamListWithHeroObjectsFromNames(String[] teamFront) {
		List<Hero> team = new ArrayList<Hero>();
        for (String heroName : teamFront) {
        	Hero hero = HeroFactory.getHeroByLocalizedName(heroName);
        	team.add(hero);
        }
		return team;
	}
	
	public void setRecommendedHeroes(Object object) {
		this.recommendedHeroes = (List<Hero>) object;
	}
	
	public List<Hero> getRecommendedHeroes() {
		return this.recommendedHeroes;
	}

	public List<Hero> getMyTeamResponse() {
		return myTeamResponse;
	}

	public void setMyTeamResponse(List<Hero> myTeamResponse) {
		this.myTeamResponse = myTeamResponse;
	}

	public List<Hero> getOppositeTeamResponse() {
		return oppositeTeamResponse;
	}

	public void setOppositeTeamResponse(List<Hero> oppositeTeamResponse) {
		this.oppositeTeamResponse = oppositeTeamResponse;
	}
	
	

}
