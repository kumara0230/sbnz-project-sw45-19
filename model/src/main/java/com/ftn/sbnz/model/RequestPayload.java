package com.ftn.sbnz.model;
public class RequestPayload {
    private String[] myTeam;
    private String[] oppositeTeam;
    private String rank;
    
    
	public String[] getMyTeam() {
		return myTeam;
	}
	public void setMyTeam(String[] myTeam) {
		this.myTeam = myTeam;
	}
	public String[] getOppositeTeam() {
		return oppositeTeam;
	}
	public void setOppositeTeam(String[] oppositeTeam) {
		this.oppositeTeam = oppositeTeam;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
    
    

}