package eg.edu.alexu.csd.oop.calculator.cs76;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Dictionary;

import static javafx.application.Application.launch;


public class Main extends Application implements Calculator  {
    @Override
    public void input(String s) {

    }

    @Override
    public String getResult() {
        return null;
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
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");
        Equal=new Button();
        Equal.setText("=");
        Equal.setOnAction(e -> {

        });
        Add=new Button();
        Add.setText("+");
        Add.setOnAction(e -> {

        });
        Subtrac=new Button();
        Subtrac.setText("-");
        Subtrac.setOnAction(e -> {

        });
        Multiply=new Button();
        Multiply.setText("X");
        Multiply.setOnAction(e -> {

        });
        Divide=new Button();
        Divide.setText("/");
        Divide.setOnAction(e -> {

        });
        VBox layout=new VBox(20);
        layout.getChildren().addAll(Equal,Add,Divide,Subtrac,Multiply);
        Scene scene=new Scene(layout,450,550);
        layout.setStyle("-fx-background-color: #111213");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
