package sample;

import datapack.Club;
import datapack.Players;
import dto.BuyRequest;
import dto.SellRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

public class Details {

    Stage stage;
    Club club;
    Players players;
    NetworkUtil networkUtil;
    public void init(Stage stage, Club club, Players players, NetworkUtil networkUtil) {
        this.players=players;
        this.club=club;
        this.stage=stage;
        this.networkUtil=networkUtil;
        name.setText(players.getName());
        country.setText(players.getCountry());
        age.setText(players.getAge()+"");
        height.setText(players.getHeight()+"");
        clubTextField.setText(players.getClub());
        position.setText(players.getPosition());
        number.setText(players.getNumber()+"");
        weeklySalary.setText(players.getWeeklySalary()+"");
    }
    void mode(String s){
        if(s.equalsIgnoreCase("buy")){
            buyButton.setVisible(true);
            priceLabel.setVisible(true);
            SellButton.setVisible(false);
            sellPlayerLabel.setVisible(false);
            priceTextField.setText(""+players.getPrice());

        }
        else if(s.equalsIgnoreCase("sell")){
            buyButton.setVisible(false);
            priceLabel.setVisible(false);
            SellButton.setVisible(true);
            sellPlayerLabel.setVisible(true);
            priceTextField.setText(null);
            priceTextField.setPromptText("Enter Price");
        }
    }

    @FXML
    private TextField name;

    @FXML
    private TextField country;

    @FXML
    private TextField age;

    @FXML
    private TextField height;

    @FXML
    private TextField clubTextField;

    @FXML
    private TextField position;

    @FXML
    private TextField number;

    @FXML
    private TextField weeklySalary;

    @FXML
    private TextField priceTextField;
    @FXML
    private Button SellButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label Warning;

    @FXML
    private Label priceLabel;

    @FXML
    private Button buyButton;

    @FXML
    private Text sellPlayerLabel;

    @FXML
    void SellButtonAction(ActionEvent event) {
        try {
            double price= Double.parseDouble(priceTextField.getText());
            if(price<0.00)throw new NumberFormatException();
            networkUtil.write(new SellRequest(club,players,price));
            //System.out.println(players.getName()+","+club.getClubName());
            stage.close();
        }catch (NumberFormatException e){
            Warning.setText("Please Enter Valid Value");
            Warning.setStyle("-fx-text-fill: " + "#ff0000");
        }catch (Exception e){
            System.out.println("Sell action interrupted");
        }
    }
    @FXML
    void buyButtonAction(ActionEvent event) {
        try{
            networkUtil.write(new BuyRequest(club,players));
            stage.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    @FXML
    void closeButtonAction(ActionEvent event) {
        stage.close();
    }

}