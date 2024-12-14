package datapack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    String clubName;
    String clubPassword;
    int playerCount;
    double clubWeeklySalary;
    List<Players> clubPlayers;
    public Club(){
        playerCount=0;
        clubWeeklySalary=0;
        clubPlayers=new ArrayList<>();
        clubPassword="password";
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public String getClubName() {
        return clubName;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    public int getPlayerCount() {
        return clubPlayers.size();
    }

    public void setClubWeeklySalary(double clubWeeklySalary) {
        this.clubWeeklySalary = clubWeeklySalary;
    }
    public double getClubWeeklySalary() {
        return clubWeeklySalary;
    }

    public void setClubPassword(String clubPassword) {
        this.clubPassword = clubPassword;
    }
    public String getClubPassword() {
        return clubPassword;
    }

    public List<Players> getClubPlayers() {
        return clubPlayers;
    }

    public void setClubPlayers(List<Players> clubPlayers) {
        this.clubPlayers = clubPlayers;
    }

    public void addPlayer(Players p){
        clubPlayers.add(p);
        playerCount++;
        clubWeeklySalary+=p.getWeeklySalary();
    }
    public void removePlayer(Players players){
        for (int i = 0; i < clubPlayers.size(); i++) {
            if(clubPlayers.get(i).getName().equalsIgnoreCase(players.getName())){
                clubPlayers.remove(i);
                playerCount--;
                clubWeeklySalary-=players.getWeeklySalary();
                break;
            }
        }
    }
    public double getMaxSalary(){
        double mx=-1.00;
        for (int i = 0; i < clubPlayers.size(); i++) {
            if(clubPlayers.get(i).getWeeklySalary()>=mx){
                mx=clubPlayers.get(i).getWeeklySalary();
            }
        }
        return mx;
    }
    public int getMaxAge(){
        int mx=-1;
        for (int i = 0; i < clubPlayers.size(); i++) {
            if(clubPlayers.get(i).getAge()>=mx){
                mx=clubPlayers.get(i).getAge();
            }
        }
        return mx;
    }
    public double getMaxHeight(){
        double mx=-1.00;
        for (int i = 0; i < clubPlayers.size(); i++) {
            if(clubPlayers.get(i).getHeight()>=mx){
                mx=clubPlayers.get(i).getHeight();
            }
        }
        return mx;
    }
    public double getClubYearlySalary(){
        return clubWeeklySalary*52;
    }
}

