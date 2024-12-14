package sample;
import datapack.*;
import datapack.Players;
import dto.UpdateAll;
import dto.UpdateAllAfterBuy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainMenu {
    Main main;
    Club club;
    List<Players> buyPlayerList;
    public void setMain(Main main){
        this.main=main;
    }

    void init(Club c,Club sellclub){
        buyPlayerList= sellclub.getClubPlayers();
        this.club=c;
        searchVbox.setVisible(false);
        othersVbox.setVisible(false);
        doubleTextfield.setVisible(false);
        singleTextField.setVisible(false);
        table.setVisible(true);
        buyPlayersHbox.setVisible(false);
        countryTable.setVisible(false);
        initiateTable(c);
        initiateCountryTable(c);
        initiateBuyTable(buyPlayerList);
    }
    ObservableList<Players> p;
    void initiateTable(Club c){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        table.getColumns().clear();
        table.getColumns().addAll(nameColumn, countryColumn, positionColumn,ageColumn,numberColumn);
        table.setEditable(true);
        table.setItems(getAllPlayers(c));
    }

    public ObservableList<Players> getAllPlayers(Club c){
        if(p!=null){p.clear();}
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByName(Club c,String name){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getName().equalsIgnoreCase(name)) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByCountry(Club c,String name){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getCountry().equalsIgnoreCase(name)) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByPosition(Club c,String pos){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getPosition().equalsIgnoreCase(pos)) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersBySalaryRange(Club c,Double low,Double high){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getWeeklySalary()>=low&&c.getClubPlayers().get(i).getWeeklySalary()<=high) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByMaxSalary(Club c){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getWeeklySalary()==c.getMaxSalary()) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByMaxHeight(Club c){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getHeight()==c.getMaxHeight()) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    public ObservableList<Players> getPlayersByMaxAge(Club c){
        p.clear();
        p= FXCollections.observableArrayList();
        for (int i = 0; i <c.getClubPlayers().size() ; i++) {
            if(!c.getClubPlayers().get(i).isPutForSale()&&c.getClubPlayers().get(i).getAge()==c.getMaxAge()) {
                p.add(c.getClubPlayers().get(i));
            }
        }
        return p;
    }
    void initiateCountryTable(Club c){
        countryNameCol.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        countryPlayerCountCol.setCellValueFactory(new PropertyValueFactory<>("playerCount"));
        countryTable.getColumns().clear();
        countryTable.getColumns().addAll(countryNameCol,countryPlayerCountCol);
        countryTable.setEditable(true);
        countryTable.setItems(getCountryList(c));
    }
    public ObservableList<Country> getCountryList(Club c) {
        ObservableList<Country> ctol=FXCollections.observableArrayList();
        List<Country> countryList=initializeCountry(c.getClubPlayers());
        for (int i = 0; i < countryList.size(); i++) {
            ctol.add(countryList.get(i));
        }
        return ctol;
    }
    public List<Country> initializeCountry(List<Players> playerList){
        List<Country> countryList=new ArrayList();
        for (int i = 0; i < playerList.size(); i++) {
            int indicator=0;
            for (int j = 0; j < countryList.size(); j++) {
                if(countryList.get(j).getCountryName().equalsIgnoreCase(playerList.get(i).getCountry())){
                    indicator=1;
                    countryList.get(j).addPlayer(playerList.get(i));
                    break;
                }
            }
            if(indicator==0) {
                Country c = new Country();
                c.setCountryName(playerList.get(i).getCountry());
                c.addPlayer(playerList.get(i));
                countryList.add(c);
            }
        }
        return countryList;
    }
    ObservableList<Players> a;
    void initiateBuyTable(List<Players> playersList){
        nameColumn1.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn1.setCellValueFactory(new PropertyValueFactory<>("country"));
        positionColumn1.setCellValueFactory(new PropertyValueFactory<>("position"));
        ageColumn1.setCellValueFactory(new PropertyValueFactory<>("age"));
        priceColumn1.setCellValueFactory(new PropertyValueFactory<>("price"));
        buyTable.getColumns().clear();
        buyTable.getColumns().addAll(nameColumn1, countryColumn1, positionColumn1,ageColumn1,priceColumn1);
        buyTable.setEditable(true);
        buyTable.setItems(getBuyPlayerList(playersList));
    }
    public ObservableList<Players> getBuyPlayerList(List<Players>playersList) {
        if(a!=null){a.clear();}
        a=FXCollections.observableArrayList();
        for (int i = 0; i < playersList.size(); i++) {
            if(club.getClubName().equalsIgnoreCase(playersList.get(i).getName())){

            }
            else{
                a.add(playersList.get(i));
            }
        }
        return a;
    }

    @FXML
    private HBox topHbox;

    @FXML
    private Button home;

    @FXML
    private Button searchPlayers;

    @FXML
    private Button buySellPlayers;

    @FXML
    private Button others;

    @FXML
    private AnchorPane leftAnchorPane;

    @FXML
    private VBox searchVbox;

    @FXML
    private Button byName;

    @FXML
    private Button byCountry;

    @FXML
    private Button byPosition;

    @FXML
    private Button bySalaryRange;

    @FXML
    private Button byMaximumSalary;

    @FXML
    private Button byMaximumAge;

    @FXML
    private Button byMaximumHeight;

    @FXML
    private VBox othersVbox;

    @FXML
    private Button countrywisePlayerCount;

    @FXML
    private Button clubYearlySalary;

    @FXML
    private VBox yearlySalaryHbox;

    @FXML
    private Label yearlySalaryLabel;

    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, String> countryNameCol;

    @FXML
    private TableColumn<Country, Integer> countryPlayerCountCol;

    @FXML
    private HBox doubleTextfield;

    @FXML
    private TextField fromTextField;

    @FXML
    private TextField toTextField;

    @FXML
    private Label doubleTextFieldWarning;

    @FXML
    private HBox singleTextField;

    @FXML
    private TextField onlyTextField;

    @FXML
    private TableView<Players> table;

    @FXML
    private TableColumn<Players,String> nameColumn;

    @FXML
    private TableColumn<Players,String> countryColumn;

    @FXML
    private TableColumn<Players,String> positionColumn;

    @FXML
    private TableColumn<Players,Integer> numberColumn;

    @FXML
    private TableColumn<Players,Integer> ageColumn;

    @FXML
    private HBox buyPlayersHbox;

    @FXML
    private TableView<Players> buyTable;

    @FXML
    private TableColumn<Players, String> nameColumn1;

    @FXML
    private TableColumn<Players, String> countryColumn1;

    @FXML
    private TableColumn<Players, String> positionColumn1;

    @FXML
    private TableColumn<Players, Double> priceColumn1;

    @FXML
    private TableColumn<Players, Integer> ageColumn1;

    @FXML
    void buySellPlayersAction(ActionEvent event) {
        buyPlayersHbox.setVisible(true);
        table.setVisible(false);
        countryTable.setVisible(false);
        yearlySalaryLabel.setVisible(false);
        searchVbox.setVisible(false);
        othersVbox.setVisible(false);
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        buyTable.setItems(getBuyPlayerList(buyPlayerList));
        buyTable.refresh();
    }

    String ongoingAction="";

    @FXML
    void searchPlayersAction(ActionEvent event) {
        buyPlayersHbox.setVisible(false);
        table.setVisible(true);
        countryTable.setVisible(false);
        yearlySalaryLabel.setVisible(false);
        searchVbox.setVisible(true);
        othersVbox.setVisible(false);
        table.setItems(getAllPlayers(club));
        table.refresh();
//        singleTextField.setVisible(false);
//        doubleTextfield.setVisible(false);
    }

    @FXML
    void byNameAction(ActionEvent event) {
        ongoingAction="byName";

        singleTextField.setVisible(true);
        doubleTextfield.setVisible(false);
        singleTextField.toFront();

    }

    @FXML
    void byPositionAction(ActionEvent event) {
        ongoingAction="byPosition";

        singleTextField.setVisible(true);
        doubleTextfield.setVisible(false);
        singleTextField.toFront();
    }

    @FXML
    void byCountryAction(ActionEvent event) {
        ongoingAction="byCountry";

        singleTextField.setVisible(true);
        singleTextField.toFront();
        doubleTextfield.setVisible(false);

    }
    @FXML
    void onlySearchAction(ActionEvent event) {
        if(ongoingAction.equalsIgnoreCase("byName")){
            table.setItems(getPlayersByName(club,onlyTextField.getText()));
            table.refresh();
        }
        if(ongoingAction.equalsIgnoreCase("byPosition")){
            table.setItems(getPlayersByPosition(club,onlyTextField.getText()));
            table.refresh();
        }
        if(ongoingAction.equalsIgnoreCase("byCountry")){
            table.setItems(getPlayersByCountry(club,onlyTextField.getText()));
            table.refresh();
        }
    }
    @FXML
    void byMaximumAgeAction(ActionEvent event) {
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        table.setItems(getPlayersByMaxAge(club));
        table.refresh();
    }

    @FXML
    void byMaximumHeightAction(ActionEvent event) {
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        table.setItems(getPlayersByMaxHeight(club));
        table.refresh();
    }

    @FXML
    void byMaximumSalaryAction(ActionEvent event) {
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        table.setItems(getPlayersByMaxSalary(club));
        table.refresh();
    }

    @FXML
    void bySalaryRangeAction(ActionEvent event) {
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(true);
        doubleTextfield.toFront();
        doubleTextFieldWarning.setVisible(false);
    }
    @FXML
    void rangeSearchAction(ActionEvent event) {
        double low,high;
        try{
            low=Double.parseDouble(fromTextField.getText());
            high=Double.parseDouble(toTextField.getText());
            table.setItems(getPlayersBySalaryRange(club,low,high));
            table.refresh();
            doubleTextFieldWarning.setVisible(false);
        }catch (NumberFormatException e){
            doubleTextFieldWarning.setVisible(true);
        }
    }
    @FXML
    void clubYearlySalaryAction(ActionEvent event) {
        yearlySalaryLabel.setVisible(true);
        yearlySalaryLabel.setText("Yearly salary of "+club.getClubName()+" is "+String.format("%.2f",club.getClubYearlySalary()));
    }

    @FXML
    void countrywisePlayerCountAction(ActionEvent event) {
        countryTable.setVisible(true);
        countryTable.setItems(getCountryList(club));
        countryTable.refresh();
    }

    @FXML
    void homeAction(ActionEvent event) {
        buyPlayersHbox.setVisible(false);
        table.setVisible(true);
        countryTable.setVisible(false);
        yearlySalaryLabel.setVisible(false);
        searchVbox.setVisible(false);
        othersVbox.setVisible(false);
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        table.setItems(getAllPlayers(club));
        table.refresh();
    }

    @FXML
    void othersAction(ActionEvent event) {
        buyPlayersHbox.setVisible(false);
        table.setVisible(false);
        countryTable.setVisible(true);
        yearlySalaryLabel.setVisible(false);
        searchVbox.setVisible(false);
        othersVbox.setVisible(true);
        singleTextField.setVisible(false);
        doubleTextfield.setVisible(false);
        countryTable.setItems(getCountryList(club));
        countryTable.refresh();
    }

    @FXML
    void sellDetailsAction(MouseEvent event) {
        if(event.getClickCount()==2){
            try {
                showDetails("buy");
            }catch (Exception e){
                System.out.println("Error in opening buy details.fxml"+e);
            }
        }
    }

    @FXML
    void viewDetailsAction(MouseEvent event) {
        if(event.getClickCount()==2){
            try {
                showDetails("sell");
            }catch (Exception e){
                System.out.println(e+" Error in opening details.fxml");
            }
        }
    }
    void showDetails(String mode) throws IOException {
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("details.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Details detailsController = loader.getController();
        if (mode.equalsIgnoreCase("sell")) {
            detailsController.init(newStage, club, table.getSelectionModel().getSelectedItem(), main.getNetworkUtil());
            System.out.println(table.getSelectionModel().getSelectedItem().getName());
        }
        else {
            detailsController.init(newStage, club, buyTable.getSelectionModel().getSelectedItem(), main.getNetworkUtil());
            System.out.println(buyTable.getSelectionModel().getSelectedItem().getName());
        }
        detailsController.mode(mode);

        // Set the stage
        newStage.setTitle("Player Card");
        newStage.setScene(new Scene(root, 400, 550));
        newStage.show();
    }
    public void update(UpdateAll u){                                                    //update after selling
        if(club.getClubName().equalsIgnoreCase(u.getNewClub().getClubName())){
            club=u.getNewClub();
            for (int i = 0; i < club.getClubPlayers().size(); i++) {
                System.out.println("updated club after sell"+club.getClubPlayers().get(i).getName());
            }
        }
        buyPlayerList.clear();

        
        for (int i = 0; i < u.getSellClub().getClubPlayers().size(); i++) {
            if(u.getSellClub().getClubPlayers().get(i).getClub().equalsIgnoreCase(club.getClubName())){

            }
            else{
                buyPlayerList.add(u.getSellClub().getClubPlayers().get(i));
                System.out.println("buyplayerlist after sell "+ u.getSellClub().getClubPlayers().get(i).getName());
            }
        }
        ObservableList<Players>a=table.getItems();
        for (Players p:a) {
            if(p.getName().equalsIgnoreCase(u.getPlayers().getName())){
                a.remove(p);
                System.out.println("removed players after sell "+p.getName());
                break;
            }
        }
        table.setItems(a);
        table.refresh();
        buyTable.setItems(getBuyPlayerList(buyPlayerList));
        buyTable.refresh();
    }

    public void update(UpdateAllAfterBuy u){//update after buying
        if(club.getClubName().equalsIgnoreCase(u.getNewClub().getClubName())){
            club=u.getNewClub();
            for (int i = 0; i < club.getClubPlayers().size(); i++) {
                System.out.println("updated club after buy "+club.getClubPlayers().get(i).getName());
            }
        }
        buyPlayerList=new ArrayList<>();

        for (int i = 0; i < u.getSellClub().getClubPlayers().size(); i++) {
            if(u.getSellClub().getClubPlayers().get(i).getClub().equalsIgnoreCase(club.getClubName())){

            }
            else{
                buyPlayerList.add(u.getSellClub().getClubPlayers().get(i));
                System.out.println("buyplayerlist "+ u.getSellClub().getClubPlayers().get(i).getName());
            }
        }
//        ObservableList<Players>a=buyTable.getItems();
//        for (Players p:a) {
//            if(p.getName().equalsIgnoreCase(u.getPlayers().getName())){
//                a.remove(p);
//                break;
//            }
//        }
        buyTable.setItems(getBuyPlayerList(buyPlayerList));
        buyTable.refresh();
    }


}

