package eg.edu.alexu.csd.oop.calculator.cs76;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;


public class AlertBox {
    public static void display(String title,String message)
    {
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label=new Label(message);
        label.setFont(new Font("Arial", 15));
        label.setTextFill(Color.web("#ffffff"));
        Button close=new Button("Sorry!");
        close.setOnAction(e-> window.close());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label,close);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #111213");

        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
