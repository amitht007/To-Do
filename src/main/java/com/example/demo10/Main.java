package com.example.demo10;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main extends Application {


    @FXML
    AnchorPane anchorPane;
@FXML
Label quoteLabel;
    public void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane=anchorPane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Parent root=fxmlLoader.load();
        Controller controller=fxmlLoader.getController();
        Controller.setyLocate(100);
        Controller.hashMapSetter(new LinkedHashMap<Parent,Integer>());
        Controller.stageSetter(stage);

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        stage.show();
        setAnchorPane(anchorPane);      //setting the anchorPane
        setQuoteLabel(quoteLabel);      //setting the quoteLabel

    }
    public String[] quotes={"Reject Modernity,Beat Weakness.", "Make it happen."}; //strings of quotes

    public void setQuoteLabel(Label quoteLabel){
            this.quoteLabel=quoteLabel;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}