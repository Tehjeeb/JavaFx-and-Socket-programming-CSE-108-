package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.NetworkUtil;
import datapack.*;

public class Main extends Application {

    public Stage stage;
    public NetworkUtil networkUtil;
    Login loginController=null;
    MainMenu mainMenuController=null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Client client=new Client("127.0.0.1",44444,this);
        networkUtil=client.getNetworkUtil();
        stage=primaryStage;
        primaryStage.setResizable(false);
        showLoginPage();
    }
    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        loginController = loader.getController();
        loginController.setMain(this);
        // Set the primary stage
        stage.setResizable(false);
        stage.setTitle("Login User");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
    public void showMainMenuPage(Club c,Club sellClub) throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mainMenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        mainMenuController = loader.getController();
        mainMenuController.setMain(this);
        mainMenuController.init(c,sellClub);

        // Set the primary stage
        stage.setTitle("Main Menu");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
    public Login getLoginController() {
        return loginController;
    }
    public MainMenu getMainMenuController() {
        return mainMenuController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
