package sample;

import util.NetworkUtil;

public class Client {
    NetworkUtil networkUtil;
    public Client(String serverAddress, int serverPort,Main main) {
        try {
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            new ReadThreadClient(networkUtil,main);
        } catch (Exception e) {
            System.out.println(e+"blah blah");
        }
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
}
