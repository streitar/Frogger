package LevelTwoBackground;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**

 * @author Andrew Streit
 */
public class LevelTwoBackground extends Application {
     private Stage window;
     //private Image perk = Perks.loadPerk();


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage; // rename for clarity

        // set up canvas

        Group levelTwoBackground = new Group();
        Canvas levelTwoCanvas = new Canvas(1275,5250);
        Canvas levelTwoCanvasLayerTwo = new Canvas(1275,5250);

         //add Perks
        //find random Y start value for perk ((0.0 to 1.0) * 5000 +375)
        Random randomYValue = new Random();
        double Yvalue;
        Yvalue = randomYValue.nextDouble() * /*5000*/ 900 + 375;

        Image perk = Perks.loadPerk();

        ImageView perkView = new ImageView();
        perkView.setImage(perk);
        perkView.setX(0.0);
        perkView.setY(Yvalue);


        TranslateTransition movePerk = new TranslateTransition();
        movePerk.setDuration(Duration.seconds(15.0));
        movePerk.setNode(perkView);
        movePerk.setToX(1275);
        movePerk.setCycleCount(1);
        movePerk.play();



        // layer one
        GraphicsContext levelTwoBackgroundGc = levelTwoCanvas.getGraphicsContext2D();
        drawBackground(levelTwoBackgroundGc);


        levelTwoBackground.getChildren().addAll(levelTwoCanvas);

       // layer two
        GraphicsContext levelTwoBackgroundLayerTwoGc = levelTwoCanvas.getGraphicsContext2D();
        drawBackgroundLayerTwo(levelTwoBackgroundLayerTwoGc);
        TopPlayerInfo.setScore(levelTwoBackgroundGc, levelTwoBackgroundGc); // include the Player info
        levelTwoCanvasLayerTwo.toFront();
        levelTwoBackground.getChildren().addAll(perkView,levelTwoCanvasLayerTwo);


        
        // GUI settings
        window.setMaxHeight(1275);
        window.setMaxWidth(1275);
        window.setScene(new Scene(levelTwoBackground));
        window.setTitle("Level 2 - Traffic Trouble");
        window.show(); 
    }
    private void drawBackground(GraphicsContext levelTwoBackgroundGc ){
        
        // Draw the water
        levelTwoBackgroundGc.setFill(Color.BLUE);
        levelTwoBackgroundGc.fillRect(0, 125, 1275, 2250);
       
        // Draw the grass sections
        levelTwoBackgroundGc.setFill(Color.GREEN);
        levelTwoBackgroundGc.fillRect(0, 2501, 1275, 250);
        levelTwoBackgroundGc.fillRect(0, 5000, 1275, 250);
        
        //Draw road section
        levelTwoBackgroundGc.setFill(Color.BLACK);
        levelTwoBackgroundGc.fillRect(0, 2750, 1275, 2250);
     
        
    }
    private void drawBackgroundLayerTwo( GraphicsContext levelTwoBackgroundLayerTwoGc){
        
        // Draw solid yellow lines
        levelTwoBackgroundLayerTwoGc.setStroke(Color.YELLOW);
        levelTwoBackgroundLayerTwoGc.setLineWidth(62.5);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 3500, 1275, 3500);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 4250, 1275, 4250);

        // Draw solid white lines.
        levelTwoBackgroundLayerTwoGc.setStroke(Color.WHITESMOKE);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 2717.75, 1275, 2717.75);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 4967.75, 1275, 4967.75);
        
        // Draw solid brown line
        levelTwoBackgroundLayerTwoGc.setStroke(Color.BURLYWOOD);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 2467.75, 1275, 2467.75);
        
        // Draw dashed lines
        levelTwoBackgroundLayerTwoGc.setStroke(Color.YELLOW);
        levelTwoBackgroundLayerTwoGc.setLineDashes(62.5);
        levelTwoBackgroundLayerTwoGc.setLineDashOffset(200);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 3125, 1275, 3125);
        levelTwoBackgroundLayerTwoGc.strokeLine(0, 4625, 1275, 4625);
        
        //Draw emergency lane markings
        levelTwoBackgroundLayerTwoGc.setFill(Color.WHITESMOKE);
        levelTwoBackgroundLayerTwoGc.fillText("EMERGENCY LANE", 200, 3875);
        levelTwoBackgroundLayerTwoGc.fillText("EMERGENCY LANE", 600, 3875);
        levelTwoBackgroundLayerTwoGc.fillText("EMERGENCY LANE", 1100, 3875);

        // Place Lilly Pad and Danger Gators
        Image lillyPad = new Image("file:LillyPadBase.png", 250, 250, false,false);
        Image underWaterGator = new Image("file:UnderWaterGator.png", 250, 250, false, false);

        //Safe Zone - Win the Level
        levelTwoBackgroundLayerTwoGc.drawImage(lillyPad,512.5,125);

        //Danger Zone - Lose Life
        levelTwoBackgroundLayerTwoGc.drawImage(underWaterGator,0,125);
        levelTwoBackgroundLayerTwoGc.drawImage(underWaterGator,255,125);
        levelTwoBackgroundLayerTwoGc.drawImage(underWaterGator,774,125);
        levelTwoBackgroundLayerTwoGc.drawImage(underWaterGator,1025,125);


}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
