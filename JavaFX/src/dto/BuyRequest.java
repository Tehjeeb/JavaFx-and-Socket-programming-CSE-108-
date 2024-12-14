package dto;

import datapack.Club;
import datapack.Players;

import java.io.Serializable;

public class BuyRequest implements Serializable {
    Club club;
    Players players;
    public BuyRequest(){

    }
    public BuyRequest(Club club,Players players){
        this.club=club;
        this.players=players;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }
}
