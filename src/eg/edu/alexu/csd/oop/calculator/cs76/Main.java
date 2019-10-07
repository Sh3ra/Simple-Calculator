package eg.edu.alexu.csd.oop.calculator.cs76;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.math.BigInteger;
import java.util.Dictionary;

import static javafx.application.Application.launch;


public class Main extends Application implements Calculator  {
    String res;
    @Override
    public void input(String s) {
        String[] arr;
        if(s.contains("+"))
        {
            arr = s.split("\\+", 2);
        }
        else if(s.contains("-"))
            arr=s.split("-",2);
        else if(s.contains("X"))
            arr=s.split("X",2);
        else arr=s.split("/",2);
        double a=Double.parseDouble(arr[0]) ;
        double b=Double.parseDouble(arr[1]) ;
        double re;
        if(s.contains("+"))
        {
            re=a+b;
            res=Double.toString(re);
        }
        else if(s.contains("-"))
        {
            re=a-b;
            res=Double.toString(re);
        }
        else if(s.contains("X"))
        {
            re=a*b;
            res=Double.toString(re);
        }else if(s.contains("/"))
        {
            re=a/b;
            res=Double.toString(re);
        }
    }

    @Override
    public String getResult() {
        return res;
    }

    @Override
    public String current() {
        return null;
    }

    @Override
    public String prev() {
        return null;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    public static void main(String[] args) {
        launch(args);
    }
    Button Equal;
    Button Add;
    Button Subtrac;
    Button Multiply;
    Button Divide;
    Button One;
    Button Two;
    Button Three;
    Button Four;
    Button Five;
    Button Six;
    Button Seven;
    Button Eight;
    Button Nine;
    Button Zero;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");
        Label input=new Label("0");
        input.setStyle("-fx-background-color: white");
        input.setFont(new Font("Arial", 30));

        Equal=new Button();
        Equal.setText("=");
        Equal.setOnAction(e -> {
            input(input.getText());
            input.setText(getResult());
        });
        Add=new Button();
        Add.setText("+");
        Add.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                input.setText(input.getText()+"+");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Subtrac=new Button();
        Subtrac.setText("-");
        Subtrac.setOnAction(e -> {
            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                input.setText(input.getText()+"-");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Multiply=new Button();
        Multiply.setText("X");
        Multiply.setOnAction(e -> {

            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                input.setText(input.getText()+"X");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Divide=new Button();
        Divide.setText("/");
        Divide.setOnAction(e -> {

            if (!(input.getText().contains("+")||input.getText().contains("-")||input.getText().contains("X")||input.getText().contains("/")))
            {
                input.setText(input.getText()+"/");
            }
            else AlertBox.display("Dude","One Operation at a time");
        });
        Zero = new Button();
        Zero.setText("0");
        Zero.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"0");
        }
        });
        One = new Button();
        One.setText("1");
        One.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"1");
        }
        else input.setText("1");
        });Two = new Button();
        Two.setText("2");
        Two.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"2");
        }
        else input.setText("2");
        });
        Three= new Button();
        Three.setText("3");
        Three.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"3");
        }
        else input.setText("3");
        });Four = new Button();
        Four.setText("4");
        Four.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"4");
        }
        else input.setText("4");
        });
        Five = new Button();
        Five.setText("5");
        Five.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"5");
        }
        else input.setText("5");
        });
        Six = new Button();
        Six.setText("6");
        Six.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"6");
        }
        else input.setText("6");
        });
        Seven = new Button();
        Seven.setText("7");
        Seven.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"7");
        }
        else input.setText("7");
        });
        Eight = new Button();
        Eight.setText("8");
        Eight.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"8");
        }
        else input.setText("8");
        });
        Nine = new Button();
        Nine.setText("9");
        Nine.setOnAction(e->{
        if(input.getText()!="0")
        {
            input.setText(input.getText()+"9");
        }
        else input.setText("9");
        });

        VBox layout=new VBox(20);
        layout.getChildren().addAll(input,Equal,Add,Divide,Subtrac,Multiply,Zero,One,Two,Three,Four,Five,Six,Seven,Eight,Nine);
        Scene scene=new Scene(layout,450,550);
        layout.setStyle("-fx-background-color: #111213");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
