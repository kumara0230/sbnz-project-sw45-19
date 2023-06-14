package com.ftn.sbnz.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ftn.sbnz.model.Hero;

public class HeroFactory {
	
	// Wrapper class to match the JSON structure
    static class HeroesWrapper {
        private Hero[] heroes;

        public Hero[] getHeroes() {
            return heroes;
        }

        public void setHeroes(Hero[] heroes) {
            this.heroes = heroes;
        }
    }
	
	public static List<Hero> getAllHeroes() {
		List<Hero> allHeroes = new ArrayList<>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
            // Read JSON file into an array of Hero objects
            HeroesWrapper heroesWrapper = objectMapper.readValue(new File("data.json"), HeroesWrapper.class);

            // Access the Hero objects and their properties
            for (Hero hero : heroesWrapper.getHeroes()) {
            	allHeroes.add(hero);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return allHeroes;
	}
	
	public static Hero getHeroByLocalizedName(String localizedName) {
		List<Hero> allHeroes = getAllHeroes();
		
		for (Hero hero : allHeroes) {
			if (hero.getLocalized_name().equals(localizedName)) {
				return hero;
			}
		}
		return null;
	}
	
	
	public static List<Hero> getOppositeTeam() {
		List<Hero> oppositeTeam = new ArrayList<>();
		
		Hero hero1 = getHeroByLocalizedName("Axe");
		Hero hero2 = getHeroByLocalizedName("Phantom Assassin");
		Hero hero3 = getHeroByLocalizedName("Bane");
		
		oppositeTeam.add(hero1);
		oppositeTeam.add(hero2);
		oppositeTeam.add(hero3);
		
		System.out.println("Opposite team: " + oppositeTeam);
		
		return oppositeTeam;
	}
	
	public static List<Hero> getMyTeam() {
		List<Hero> myTeam = new ArrayList<>();
		
		Hero hero1 = getHeroByLocalizedName("Tusk");
		Hero hero2 = getHeroByLocalizedName("Sven");
		Hero hero3 = getHeroByLocalizedName("Sniper");
		
		myTeam.add(hero1);
		myTeam.add(hero2);
		myTeam.add(hero3);
		
		System.out.println("My team: " + myTeam);
		
		return myTeam;
	}
	
	
}
