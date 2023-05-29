package com.ftn.sbnz.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ftn.sbnz.model.Hero;

public class HeroFactory {
	
	public static List<Hero> getAllHeroes() {
		List<Hero> allHeroes = new ArrayList<>();
		
		allHeroes.add(new Hero("id1", "Hero1", "MID", new ArrayList<String>(Arrays.asList("id7")), new ArrayList<String>(Arrays.asList("id2")), new ArrayList<String>(Arrays.asList("id3"))));
        allHeroes.add(new Hero("id2", "Hero2", "CARRY", new ArrayList<String>(Arrays.asList("id1")), new ArrayList<String>(Arrays.asList("id3")), new ArrayList<String>(Arrays.asList("id4"))));
        allHeroes.add(new Hero("id3", "Hero3", "TANK", new ArrayList<String>(Arrays.asList("id2")), new ArrayList<String>(Arrays.asList("id4")), new ArrayList<String>(Arrays.asList("id5"))));
        allHeroes.add(new Hero("id4", "Hero4", "JUNGLER", new ArrayList<String>(Arrays.asList("id3")), new ArrayList<String>(Arrays.asList("id5")), new ArrayList<String>(Arrays.asList("id6"))));
        allHeroes.add(new Hero("id5", "Hero5", "JUNGLER", new ArrayList<String>(Arrays.asList("id4")), new ArrayList<String>(Arrays.asList("id6")), new ArrayList<String>(Arrays.asList("id7"))));
        allHeroes.add(new Hero("id6", "Hero6", "JUNGLER", new ArrayList<String>(Arrays.asList("id5")), new ArrayList<String>(Arrays.asList("id7")), new ArrayList<String>(Arrays.asList("id1"))));
        allHeroes.add(new Hero("id7", "Hero7", "SUPPORT", new ArrayList<String>(Arrays.asList("id6")), new ArrayList<String>(Arrays.asList("id1")), new ArrayList<String>(Arrays.asList("id2"))));
		
		return allHeroes;
	}
	
	public static List<Hero> getOppositeTeam() {
		List<Hero> oppositeTeam = new ArrayList<>();
		
        oppositeTeam.add(new Hero("id3", "Hero3", "TANK", new ArrayList<String>(Arrays.asList("id2")), new ArrayList<String>(Arrays.asList("id4")), new ArrayList<String>(Arrays.asList("id5"))));
        oppositeTeam.add(new Hero("id4", "Hero4", "JUNGLER", new ArrayList<String>(Arrays.asList("id3")), new ArrayList<String>(Arrays.asList("id5")), new ArrayList<String>(Arrays.asList("id6"))));
		
		return oppositeTeam;
	}
	
	public static List<Hero> getMyTeam() {
		List<Hero> myTeam = new ArrayList<>();
		
        myTeam.add(new Hero("id1", "Hero1", "MID", new ArrayList<String>(Arrays.asList("id7")), new ArrayList<String>(Arrays.asList("id2")), new ArrayList<String>(Arrays.asList("id3"))));
        myTeam.add(new Hero("id2", "Hero2", "CARRY", new ArrayList<String>(Arrays.asList("id1")), new ArrayList<String>(Arrays.asList("id3")), new ArrayList<String>(Arrays.asList("id4"))));
		
		return myTeam;
	}
	
	
}
