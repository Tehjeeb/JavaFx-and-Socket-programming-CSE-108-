package service;

import datapack.*;
import dto.*;
import util.NetworkUtil;

import java.io.IOException;
import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ReadThreadServer implements Runnable {

    Thread thread;
    NetworkUtil networkUtil;
    List<Club>clubList;
    HashMap<NetworkUtil,String> loggedIn;
    List<Players> availableForSell;
    ReadThreadServer(List<Club> clubList, NetworkUtil networkUtil,HashMap<NetworkUtil,String>loggedIn,List<Players> availableForSell){
        this.loggedIn =loggedIn;
        this.availableForSell=availableForSell;
        this.clubList=clubList;
        this.networkUtil=networkUtil;
        thread=new Thread(this,"Read Thread Server");
        thread.start();
    }

    @Override
    public void run() {
        try{
            while(true){
                Object ob = networkUtil.read();
                if(ob instanceof SignIn){
                    SignIn si=(SignIn) ob;
                    SignInResponse sir=new SignInResponse();
                    for (int i = 0; i <clubList.size() ; i++) {
                        if(clubList.get(i).getClubName().equalsIgnoreCase(si.getUserName().trim())){
                            sir.setUseNameMatch(true);
                            if(clubList.get(i).getClubPassword().equals(si.getPassword())){
                                sir.setPassWordMatch(true);
                                sir.setClub(clubList.get(i));
                                loggedIn.put(networkUtil,sir.getClub().getClubName());
                                System.out.println(networkUtil+", "+loggedIn.size());
                                break;
                            }
                        }
                    }
                    Club sellClub=new Club();
                    sellClub.setClubName("sell club");
                    sellClub.setClubPlayers(availableForSell);
                    sir.setSellClub(sellClub);
                    networkUtil.write(sir);
                }
                else if(ob instanceof SellRequest){
                    SellRequest sr=(SellRequest) ob;
                    Club oldClub= sr.getClub();
                    Club newClub=null;
                    availableForSell.add(sr.getPlayer());
                    for (int i = 0; i < clubList.size(); i++) {
                        if(clubList.get(i).getClubName().equalsIgnoreCase(sr.getClub().getClubName())){
                            clubList.get(i).removePlayer(sr.getPlayer());
                            newClub=clubList.get(i);
                        }
                    }
//                    for (int i = 0; i < newClub.getClubPlayers().size(); i++) {
//                        System.out.println("server deduct "+newClub.getClubPlayers().get(i).getName());
//                    }
                    Club sellClub=new Club();
                    sellClub.setClubName("sell club");
                    sellClub.setClubPlayers(availableForSell);
//                    for (int i = 0; i < availableForSell.size(); i++) {
//                        System.out.println("server deduct selllist "+availableForSell.get(i).getName());
//                    }
                    Iterator<NetworkUtil> iterator = loggedIn.keySet().iterator();
                    while (iterator.hasNext()){
                        NetworkUtil networkUtil1=iterator.next();
                        networkUtil1.write(new UpdateAll(oldClub,newClub,sellClub,sr.getPlayer()));
                        //System.out.println(sr.getPlayer().getName()+","+sr.getClub().getClubName());
                    }
//                    for (int i = 0; i < newClub.getClubPlayers().size(); i++) {
//                        System.out.println("server sellreq new club "+newClub.getClubPlayers().get(i).getName());
//                    }
//                    for (int i = 0; i < sellClub.getClubPlayers().size(); i++) {
//                        System.out.println("server sellreq buy club "+sellClub.getClubPlayers().get(i).getName());
//                    }
//                    System.out.println("sold player"+sr.getPlayer().getName()+" , "+sr.getPlayer().getClub());
                }
                else if(ob instanceof BuyRequest){
                    BuyRequest br=(BuyRequest) ob;
                    Club oldClub= br.getClub();
                    Club newClub=null;
                    Players oldPlayer=new Players(br.getPlayers().getName(),br.getPlayers().getCountry(),br.getPlayers().getAge(),br.getPlayers().getHeight(),br.getPlayers().getClub(),br.getPlayers().getPosition(),br.getPlayers().getNumber(),br.getPlayers().getWeeklySalary());
                    //remove from sell list
                    for (int i = 0; i < availableForSell.size(); i++) {
                        //System.out.println("server add selllist "+availableForSell.get(i).getName());
                        if(availableForSell.get(i).getName().equalsIgnoreCase(br.getPlayers().getName())){
                            availableForSell.remove(i);
                            break;
                        }
                    }

                    for (int i = 0; i < clubList.size(); i++) {
                        if(clubList.get(i).getClubName().equalsIgnoreCase(br.getClub().getClubName())){
                            br.getPlayers().setClub(br.getClub().getClubName());
                            clubList.get(i).addPlayer(br.getPlayers());
                            newClub=clubList.get(i);
                            break;
                        }
                    }
//                    for (int i = 0; i < newClub.getClubPlayers().size(); i++) {
//                        System.out.println("server add "+newClub.getClubPlayers().get(i).getName());
//                    }
                    Club buyClub=new Club();
                    buyClub.setClubName("buy club");
                    buyClub.setClubPlayers(availableForSell);
//                    for (int i = 0; i < availableForSell.size(); i++) {
//                        System.out.println("server add selllist "+availableForSell.get(i).getName());
//                    }
                    Iterator<NetworkUtil> iterator = loggedIn.keySet().iterator();
                    while (iterator.hasNext()){
                        NetworkUtil networkUtil1=iterator.next();
                        networkUtil1.write(new UpdateAllAfterBuy(oldClub,newClub,buyClub,oldPlayer));
                    }
//                    for (int i = 0; i < newClub.getClubPlayers().size(); i++) {
//                        System.out.println("server buyreq new club "+newClub.getClubPlayers().get(i).getName());
//                    }
//                    for (int i = 0; i < buyClub.getClubPlayers().size(); i++) {
//                        System.out.println("server buyreq buy club "+buyClub.getClubPlayers().get(i).getName());
//                    }
//                    System.out.println("old player"+oldPlayer.getName()+" , "+oldPlayer.getClub());
                }
            }


        }catch (Exception e){
            System.out.println(e+"ReadThreadServer");
        }
        finally {
            try{
                networkUtil.closeConnection();
            }catch (Exception e){
                System.out.println("couldn't close connection");
            }
        }

    }
}
