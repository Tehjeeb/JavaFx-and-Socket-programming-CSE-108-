package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import datapack.*;
import util.NetworkUtil;
import javafx.scene.control.Button;
public class Server {
    private ServerSocket serverSocket;
    public List<Club> clubList;
    public List<Players> playerList;
    public List<Players> availableForSale;
    //HashMap<NetworkUtil,String> hashMap=new HashMap<>();;
    Server() throws Exception {
        HashMap<NetworkUtil,String >hashMap=new HashMap<>();
        playerList=ServerInitialize.readFromFile();
        availableForSale= Collections.synchronizedList(new ArrayList<>());
        clubList=ServerInitialize.initializeClub(playerList);
        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket,hashMap,availableForSale);
            }
        } catch (Exception e) {
            System.out.println("server.Server starts:" + e);
        }
    }
    public void serve(Socket clientSocket,HashMap<NetworkUtil,String>hashMap,List<Players> availableForSale) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(clubList, networkUtil,hashMap,availableForSale);
    }

    public static void main(String[] args) throws Exception {
        Server server=new Server();
    }

}
