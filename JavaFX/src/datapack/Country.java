package datapack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Country implements Serializable {
    String countryName;
    int playerCount;
    List<Players> countryPlayers;
    public Country(){
        playerCount=0;
        countryPlayers=new ArrayList();
        countryName="Not set";
    }

    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPlayerCount() {
        return playerCount;
    }
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void addPlayer(Players p){
        countryPlayers.add(p);
        playerCount++;
    }
}