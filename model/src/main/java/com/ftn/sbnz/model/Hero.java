package com.ftn.sbnz.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Hero implements Comparable<Hero>{
    private String name; //kao neki id-name
    private String role;
    private ArrayList<String> good_against;
    private ArrayList<String> bad_against;
    private ArrayList<String> combo;
    private int meta;
    private int player_skills;
    private String rank_play;
    private int id;
    private String localized_name;  // ime koje je predstavljeno
    
    private int score = 0;
    
    @JsonCreator
	public Hero(@JsonProperty("name") String name, 
				@JsonProperty("role") String role, 
				@JsonProperty("good_against") ArrayList<String> goodAgainst, 
				@JsonProperty("bad_against") ArrayList<String> badAgainst,
				@JsonProperty("combo") ArrayList<String> combo, 
				@JsonProperty("meta") int meta, 
				@JsonProperty("player_skills") int player_skills, 
				@JsonProperty("rank_play") String rank_play, 
				@JsonProperty("id") int id, 
				@JsonProperty("localized_name") String localized_name) {
		super();
		this.name = name;
		this.role = role;
		this.good_against = goodAgainst;
		this.bad_against = badAgainst;
		this.combo = combo;
		this.meta = meta;
		this.player_skills = player_skills;
		this.rank_play = rank_play;
		this.id = id;
		this.localized_name = localized_name;
	}
	
	public Hero() {
    }
	
	@Override
    public int compareTo(Hero otherHero) {
        // Compare heroes based on their score in descending order
        return Integer.compare(otherHero.score, this.score);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<String> getGoodAgainst() {
		return good_against;
	}

	public void setGoodAgainst(ArrayList<String> goodAgainst) {
		this.good_against = goodAgainst;
	}

	public ArrayList<String> getBadAgainst() {
		return bad_against;
	}

	public void setBadAgainst(ArrayList<String> badAgainst) {
		this.bad_against = badAgainst;
	}

	public ArrayList<String> getCombo() {
		return combo;
	}

	public void setCombo(ArrayList<String> combo) {
		this.combo = combo;
	}

	public int getMeta() {
		return meta;
	}

	public void setMeta(int meta) {
		this.meta = meta;
	}

	public int getPlayer_skills() {
		return player_skills;
	}

	public void setPlayer_skills(int player_skills) {
		this.player_skills = player_skills;
	}

	public String getRank_play() {
		return rank_play;
	}

	public void setRank_play(String rank_play) {
		this.rank_play = rank_play;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalized_name() {
		return localized_name;
	}

	public void setLocalized_name(String localized_name) {
		this.localized_name = localized_name;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score = this.score + score;
	}
	@Override
	public String toString() {
	    return "Hero{" +
	            "name='" + name + '\'' +
	            ", role='" + role + '\'' +
	            ", good_against=" + good_against +
	            ", bad_against=" + bad_against +
	            ", combo=" + combo +
	            ", meta=" + meta +
	            ", player_skills=" + player_skills +
	            ", rank_play='" + rank_play + '\'' +
	            ", id=" + id +
	            ", localized_name='" + localized_name + '\'' +
	            '}';
	}

    


}
