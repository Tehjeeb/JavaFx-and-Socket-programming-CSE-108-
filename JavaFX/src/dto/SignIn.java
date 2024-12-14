package dto;

import java.io.Serializable;

public class SignIn implements Serializable {
    String userName,password;
    public SignIn(String userName, String password){
        this.password=password;
        this.userName=userName;
    }
    public String getPassword() {
        return password;
    }
    public String getUserName() {
        return userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
