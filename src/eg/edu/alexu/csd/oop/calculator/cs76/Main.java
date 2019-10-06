package eg.edu.alexu.csd.oop.calculator.cs76;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Main extends Application implements EventHandler<ActionEvent>, Calculator  {
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
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Calculator");
        Equal=new Button();
        Equal.setText("=");
        Equal.setOnAction(this);
        StackPane layout=new StackPane();
        layout.getChildren().add(Equal);
        Scene scene=new Scene(layout,450,550);
        layout.setStyle("-fx-background-color: #111213");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==Equal)
        {

        }
    }
}
