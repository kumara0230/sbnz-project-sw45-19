package com.ftn.sbnz.service.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.Hero;
import com.ftn.sbnz.service.HeroFactory;

public class AllTests {

	@Test
	public void test1() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kc = ks.newKieClasspathContainer();
		KieSession session = kc.newKieSession("session");
		
        session.setGlobal("player_rank", "LOW");
        
        List<Hero> myTeam = HeroFactory.getMyTeam();
        session.insert(myTeam);
        session.setGlobal("myTeam", myTeam);
        
        List<Hero> oppositeTeam = HeroFactory.getOppositeTeam();
        session.setGlobal("oppositeTeam", oppositeTeam);
        
        List<String> pickedHeroes = fillPickedHeroes(myTeam, oppositeTeam);
        session.setGlobal("pickedHeroes", pickedHeroes);
        
        List<String> counterPicks = fillCounterPicks(oppositeTeam);
        session.setGlobal("counterPicks", counterPicks);
        
        List<String> combos = fillCombos(myTeam);
        session.setGlobal("combos", combos);
        
        List<Hero> recommendedHeroes = new ArrayList<Hero>();
        
    	session.setGlobal("recommendedHeroes", recommendedHeroes);
    	session.fireAllRules();
        
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

}
