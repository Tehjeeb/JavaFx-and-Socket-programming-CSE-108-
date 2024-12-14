package dto;

import datapack.*;

import java.io.Serializable;

public class SellRequest implements Serializable {
    Club club;
    Players player;
    public SellRequest(){

    }
    public SellRequest(Club club, Players player, double price){
        this.club=club;
        this.player=player;
        this.player.setPrice(price);
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }
}
