package application;

import java.text.DecimalFormat;
/**
 * <h1>COMP2130 Assignment 2</h1>
 * 
 * @author Emmanuel Sogelola - 101203022
 * @author Stefan Maric - 101208175
 * @author Kevin Sabas - 101049251
 *
 */
public class Member {
private String  firstName, lastName, winPercentage;
private int numGamesPlayed,numWins, id, numLosses;
private double winRatio;


public Member(int id, String firstName, String lastName, int numGames, int numWins) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.numGamesPlayed = numGames;
	this.numWins = numWins;
	this.numLosses = numGamesPlayed - numWins;
	this.winRatio = ((double)numWins/(double)numGamesPlayed) * 100;
	this.winPercentage = new DecimalFormat("#.00").format(winRatio);
}

public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public int getNumGamesPlayed() {
	return numGamesPlayed;
}
public void setNumGamesPlayed(int numGamesPlayed) {
	this.numGamesPlayed = numGamesPlayed;

}
public String getWinPercentage() {
	return winPercentage + "%";
}

public int getNumWins() {
	return numWins;
}
public void setNumWins(int numWins) {
	this.numWins = numWins;
	
}
public void updateLosses() {
	this.numLosses = numGamesPlayed - numWins;
	this.winRatio = ((double)numWins/(double)numGamesPlayed) * 100;
	this.winPercentage = new DecimalFormat("#.00").format(winRatio) + "%"; 
}
public int getId() {
	return id;
}
public int getNumLosses() {
	return numLosses;
}
public double getWinRatio() {

	return winRatio;
}

public String toString() {

	String fullName = (getFirstName()  + getLastName());
	
	 return  getId() +" "+ fullName +" "+getNumGamesPlayed() +" "+getNumWins() +" "+getNumLosses() +" "+ getWinRatio();

}



}
