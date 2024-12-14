package dto;

import datapack.Club;
import datapack.Players;

import java.io.Serializable;
import java.util.List;

public class UpdateAll implements Serializable {
    Club oldClub;
    Club newClub;
    Club sellClub;
    Players players;
    public UpdateAll(){

    }

    public UpdateAll(Club oldClub, Club newClub, Club sellClub, Players players) {
        this.oldClub = oldClub;
        this.newClub = newClub;
        this.sellClub = sellClub;
        this.players = players;
    }

    public Club getOldClub() {
        return oldClub;
    }

    public void setOldClub(Club oldClub) {
        this.oldClub = oldClub;
    }

    public Club getNewClub() {
        return newClub;
    }

    public void setNewClub(Club newClub) {
        this.newClub = newClub;
    }

    public Club getSellClub() {
        return sellClub;
    }

    public void setSellClub(Club sellClub) {
        this.sellClub = sellClub;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }
}
