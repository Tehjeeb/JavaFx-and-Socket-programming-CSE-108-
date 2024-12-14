package service;

import datapack.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ServerInitialize {

    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";

    public static List<Players> readFromFile() throws Exception {
        List<Players> playerList = Collections.synchronizedList(new ArrayList());
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Players p = new Players();
            p.setName(tokens[0]);
            p.setCountry(tokens[1]);
            p.setAge(Integer.parseInt(tokens[2]));
            p.setHeight(Double.parseDouble(tokens[3]));
            p.setClub(tokens[4]);
            p.setPosition(tokens[5]);
            p.setNumber(Integer.parseInt(tokens[6]));
            p.setWeeklySalary(Double.parseDouble(tokens[7]));
            playerList.add(p);
        }
        br.close();

        return playerList;
    }
    public static List<Club> initializeClub(List<Players> playerList){
        List<Club> clubList=Collections.synchronizedList(new ArrayList());
        for (int i = 0; i < playerList.size(); i++) {
            int indicator=0;
            for (int j = 0; j < clubList.size(); j++) {
                if(clubList.get(j).getClubName().equalsIgnoreCase(playerList.get(i).getClub())){
                    indicator=1;
                    clubList.get(j).addPlayer(playerList.get(i));
                    break;
                }
            }
            if(indicator==0) {
                Club c = new Club();
                c.setClubName(playerList.get(i).getClub());
                c.addPlayer(playerList.get(i));
                clubList.add(c);
            }
        }
        return clubList;
    }


}
