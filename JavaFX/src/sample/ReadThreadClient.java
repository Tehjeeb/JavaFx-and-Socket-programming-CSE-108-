package sample;

import dto.*;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import util.NetworkUtil;

public class ReadThreadClient implements Runnable{

    Thread thread;
    NetworkUtil networkUtil;
    Main main;
    ReadThreadClient(NetworkUtil networkUtil,Main main){
        this.main=main;
        this.networkUtil=networkUtil;
        thread=new Thread(this,"Client Read Thread");
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object ob=networkUtil.read();
                if(ob instanceof SignInResponse){
                    SignInResponse sir=(SignInResponse) ob;
                    if(sir.isPassWordMatch()&&sir.isUseNameMatch()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.showMainMenuPage(sir.getClub(),sir.getSellClub());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else{
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    main.getLoginController().errorMessageLabel.setText("Wrong Username/Password");
                                    main.getLoginController().errorMessageLabel.setStyle("-fx-text-fill: " + "#ff0000");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
                else if(ob instanceof UpdateAll){
                    UpdateAll ua=(UpdateAll) ob;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                main.mainMenuController.update(ua);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if(ob instanceof UpdateAllAfterBuy){
                    UpdateAllAfterBuy uaab=(UpdateAllAfterBuy) ob;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                main.mainMenuController.update(uaab);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }




            }
        }catch (Exception e){
            System.out.println(e+"blue blah");
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
