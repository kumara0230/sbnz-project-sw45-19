package com.ftn.sbnz.model;

import java.util.ArrayList;

public class Hero {
    private String ID;
    private String name;
    private String role;
    private ArrayList<String> goodAgainst;
    private ArrayList<String> badAgainst;
    private ArrayList<String> combo;
    
	public Hero(String iD, String name, String role, ArrayList<String> goodAgainst, 
			ArrayList<String> badAgainst, ArrayList<String> combo) {
		super();
		ID = iD;
		this.name = name;
		this.role = role;
		this.goodAgainst = goodAgainst;
		this.badAgainst = badAgainst;
		this.combo = combo;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
		return goodAgainst;
	}
	public void setGoodAgainst(ArrayList<String> goodAgainst) {
		this.goodAgainst = goodAgainst;
	}
	public ArrayList<String> getBadAgainst() {
		return badAgainst;
	}
	public void setBadAgainst(ArrayList<String> badAgainst) {
		this.badAgainst = badAgainst;
	}
	public ArrayList<String> getCombo() {
		return combo;
	}
	public void setCombo(ArrayList<String> combo) {
		this.combo = combo;
	}
    


}
