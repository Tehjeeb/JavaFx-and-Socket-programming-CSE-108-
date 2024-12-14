package sample;

import dto.SignIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.NetworkUtil;

public class Login {

    Main main;
    public void setMain(Main main){
        this.main=main;
    }

    String str="",pass="";
    SignIn signInOb;
    boolean checker;
    @FXML
    private Button signIn;

    @FXML
    private TextField clubNameText;

    @FXML
    private PasswordField clubPasswordText;

    @FXML
    private Text close;

    @FXML
    private Label userLabel;

    @FXML
    public Label errorMessageLabel;

    @FXML
    void signInAction(ActionEvent event) throws Exception {
        str=clubNameText.getText();
        pass=clubPasswordText.getText();
        signInOb=new SignIn(str,pass);
        main.getNetworkUtil().write(signInOb);
    }

    @FXML
    void closeAction(MouseEvent event) {
        main.stage.close();
    }

    @FXML
    void closeActionMouseEntered(MouseEvent event) {

    }

    @FXML
    void closeActionMouseExited(MouseEvent event) {

    }

    @FXML
    void userLabelAction(MouseEvent event) {

    }


}

