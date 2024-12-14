package datapack;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import java.awt.*;
import java.io.Serializable;

public class Players implements Serializable {
    private String name,country,club;
    private int age,number;
    private double height,weeklySalary;
    private String position;
    private boolean isPutForSale;
    private double price;
    public Players(){
        isPutForSale=false;
        price=0.00;
        name="";
    }

    public Players(String name,String country,int age,double height,String club,String position,int number,double weeklySalary){
        this.name=name;
        this.country=country;
        this.age=age;
        this.height=height;
        this.club=club;
        this.position=position;
        this.number=number;
        this.weeklySalary=weeklySalary;
        isPutForSale=false;
        price=0;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public void setClub(String club) {
        this.club = club;
    }
    public String getClub() {
        return club;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }
    public void setWeeklySalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isPutForSale() {
        return isPutForSale;
    }
    public void setPutForSale(boolean putForSale) {
        isPutForSale = putForSale;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void print(){
        System.out.println(name+","+country+","+age+","+height+","+club+","+position+","+number+","+weeklySalary);
    }

}

