package dto;

import datapack.*;

import java.io.Serializable;

public class SignInResponse implements Serializable {
    boolean useNameMatch;
    boolean passWordMatch;
    Club club;
    Club sellClub;
    public SignInResponse(){
        useNameMatch=false;
        passWordMatch=false;
        club=null;
        sellClub=null;
    }

    public boolean isUseNameMatch() {
        return useNameMatch;
    }

    public boolean isPassWordMatch() {
        return passWordMatch;
    }

    public void setClub(Club club) {
        this.club = club;
    }
    public Club getClub() {
        return club;
    }

    public Club getSellClub() {
        return sellClub;
    }
    public void setSellClub(Club sellClub) {
        this.sellClub = sellClub;
    }

    public void setUseNameMatch(boolean useNameMatch) {
        this.useNameMatch = useNameMatch;
    }

    public void setPassWordMatch(boolean passWordMatch) {
        this.passWordMatch = passWordMatch;
    }


}
