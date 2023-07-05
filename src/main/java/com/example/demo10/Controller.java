package com.example.demo10;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller {
    @FXML
    private Button addButton;

    @FXML
    private Pane taskPane;
    @FXML
    private Button send;
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Label labelShow;
    @FXML
    private TextField text;
    @FXML
    private Button startButton,resetButton,setButton;
    int xLocate;
    static int yLocate;
    static public void setyLocate(int y) {
        yLocate = y;
    }
    static public int getyLocate() {
        return yLocate;
    }

    Parent rootDum;
    static LinkedHashMap<Parent,Integer> parentMap;
    public static void hashMapSetter(LinkedHashMap<Parent,Integer> ap){
        parentMap=ap;

    }
    int i=0;
    Controller controller;
    @FXML
    private Button spacer;

    @FXML
    Scene scene;

    public void sceneSetter(Scene scene){
        this.scene=scene;
    }
    @FXML
    public Label timeLabel;
    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    @FXML
    static
    Stage stage;

    @FXML
    Label quoteLabel;
    public void setQuoteLabel(Label quoteLabel){
        this.quoteLabel=quoteLabel;
    }
    public void addTask(ActionEvent event) throws IOException {
        //anchorPane.setStyle ("-fx-background-color: rgba(0, 0, 0, 0.31);");      // 69% transparent black

        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("TaskBar.fxml"));
        rootDum=fxmlLoader.load();
        controller=fxmlLoader.getController();
        System.out.println("While adding "+controller.getyLocate());
        //this is the controller i'm setting here
        controller.setController(controller);
        rootDum.setLayoutY(controller.getyLocate());
        parentMap.put(rootDum,controller.getyLocate());
        System.out.println("THIS IS THE GODDAMN VALUE"+controller.getyLocate());
        anchorPane.getChildren().add(rootDum);
        controller.setQuoteLabel(quoteLabel);
        controller.setyLocate(controller.getyLocate()+100);
        System.out.println("New Location For addition "+getyLocate());
        //timeLabel here
        //controller.setTimeLabel(timeLabel);
        controller.setLabel(labelShow);
        controller.sceneSetter(scene);
        controller.setAnchorPane(anchorPane);
        controller.stageSetter(stage);

        //  controller.setStartButton(startButton);


        //setting the timeLine here

        int randomInRange = (int) (Math.random() * 2); //quotes randomizer
        quoteLabel.setText(quotes[randomInRange]);

        stage.setHeight(stage.getHeight()+50.0);
        Button button =(Button)event.getSource ();

        Integer valueDum=parentMap.get(rootDum);

        System.out.println(""+valueDum);

    }
    public static void stageSetter(Stage a){
        stage=a;
    }
    public void setLabel(Label labelShow) {
        this.labelShow = labelShow;
    }
    public void send(){
        System.out.println(text.getText());
        String te=text.getText();
        labelShow.setText(text.getText());
    }
    public void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane=anchorPane;
    }
    public String[] quotes={"Reject Modernity,Beat Weakness.", "Make it happen."}; //strings of quotes


    public void setController(Controller controller){
        this.controller=controller;
    }
    //REMOVE TASKS
    public void removeTask(ActionEvent event){

        Button button =(Button)event.getSource();
        Parent rootRem=(Parent)button.getParent();
        Integer valueDum=parentMap.get(rootRem);

        System.out.println(""+valueDum);
        controller.reLocateTasks(rootRem,valueDum);
        anchorPane.getChildren().remove(rootRem);
        controller.setyLocate(controller.getyLocate()-100);
        //setting the timeLine here

        System.out.println(controller.getyLocate());
        System.out.println("New Location After Removal ");
    }


    //RELOCATE ALL THE TASKS
    public void reLocateTasks(Parent dum,Integer keyValue){
        final double stageHeight=stage.getHeight();
        int value=(int)keyValue;
        parentMap.remove(dum,value);
        int val=100;

        for (Map.Entry<Parent, Integer> entry : parentMap.entrySet()) {

            Parent dummyRef=entry.getKey();
            dummyRef.setLayoutY(val);
            parentMap.put(dummyRef,value);
            val=val+100;

            if(stage.getHeight()>=stageHeight) {
                stage.setHeight(stage.getHeight() - 50.0);
            }
            //we will iterate through each object
            //we will update the values starting from 100,200,300,and so on

        }
    }


    public boolean getRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    boolean isRunning;

    @FXML
    private TextField hour,minute,second;

    int seconds,minutes,hours;
    public void setButton(){
        int randomInRange = (int) (Math.random() * 2);
        quoteLabel.setText(quotes[randomInRange]);
            seconds = Integer.parseInt(second.getText());
            minutes = Integer.parseInt(minute.getText());
            hours = Integer.parseInt(hour.getText());
            System.out.println(hours + "" + minutes + "" + seconds);
            setRunning(false);
            timeLabel.setText("READY?");
            startButton.setText("Start");

    }

    int elapsedTime;

    //Start Timer here
    Timer timer;

     public void startTimer() {                 //when start button is called
         int randomInRange = (int) (Math.random() * 2);
         quoteLabel.setText(quotes[randomInRange]);

        if(!getRunning()){              //check whether the task is Running or not? If not running

            System.out.println("The Time has Started!");//start these
        setRunning(true);
        elapsedTime = (hours * 60 * 60) + (minutes * 60) + seconds;
        startButton.setText("DONE?");
        System.out.println("Okay Something is going in the right Direction!");

        timer = new Timer();                    //the Task starts from
        timer.scheduleAtFixedRate(new TimerTask() {

                //here is the second checker
            public void run() {
                if (elapsedTime<=1) {
                    timer.cancel();
                    Platform.runLater(() -> {
                        timeLabel.setText("Done!");
                        startButton.setText("Done!");
                    });
                }

                elapsedTime = (hours * 60 * 60) + (minutes * 60) + seconds;
                hours = (elapsedTime / 3600);
                minutes = (elapsedTime / 60);
                seconds = elapsedTime % 60;

                System.out.println("The code is in the least working properly ");
                String seconds_string = String.format("%02d", seconds);
                String minutes_string = String.format("%02d", minutes);
                String hours_string = String.format("%02d", hours);
                //timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
                System.out.println(hours_string + ":" + minutes_string + ":" + seconds_string);
                String timeTable = hours_string + ":" + minutes_string + ":" + seconds_string;
                Platform.runLater(() -> {
                    timeLabel.setText(timeTable);
                });

                seconds--;
            }
        }, 0, 1000); // Delay of 0 milliseconds, repeat every 1000 milliseconds (1 second)

    }                 //stopTimer

            if(getRunning()){                               //here isRunning is true
                startButton.setOnAction(e->
                {  timer.cancel();         setRunning(false);isRunning=false;                 //stop the programme
                Platform.runLater(() -> {               //declare you won!
                    timeLabel.setText("DONE!");     //timeLabel will show Done
                    startButton.setText("Done!");//start button will show Done
                    });
                });
                isRunning=false;                        //here isRunning is false
            }

    }


}