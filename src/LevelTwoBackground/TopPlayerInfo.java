package LevelTwoBackground;

import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;

import static java.lang.Thread.sleep;

import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class TopPlayerInfo {

    private final static Integer levelTime = 60;
    private static Integer startTimeSec = levelTime; // Variable set to 60 s

    //private boolean isRunning; // Variable to check if the timer is running
    private static String  timerText; // Label to contain the timer value


    public static void setScore(GraphicsContext topInfo, GraphicsContext topInfoBackground) {


        topInfoBackground.setFill(Color.DARKGRAY);
        topInfoBackground.fillRect(0,0, 1275, 125);

        topInfo.setFill( Color.BLACK );
        topInfo.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 48 );
        topInfo.setFont(theFont);

        startTime();

        topInfo.fillText("Score: " + score, 550, 50); // Coordinates to draw the player's score on the canvas
        topInfo.fillText("Time: " + timerText, 1050, 50); // Coordinates to draw the time left on the canvas

        drawLives(topInfo);

    }
    public static void startTime() {

        Timeline gamerTimer = new Timeline();
        gamerTimer.setCycleCount(Timeline.INDEFINITE);
        gamerTimer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                //Decrement the starting time
                startTimeSec = startTimeSec -1;
                timerText = startTimeSec.toString();

            if(startTimeSec <= 0) {
                gamerTimer.stop();
            }
            //boolean isSecondsZero = startTimeSec == 0;
            //boolean timeToChangeBackground = startTimeSec == 0;

            /*if(isSecondsZero) {
                startTimeSec = 60; // Starting value for the timer
            }

            if(timeToChangeBackground) {
                timeline.stop();
                startTimeSec = 0;
            }*/

            //timerText.setText(String.format("%d sec", startTimeSec));
        }}));
        gamerTimer.playFromStart();


        //isRunning = true;

    }


    /**
     Draws the player life count.
     @param drawLivesgc is the name of the GraphicsContext canvas variable.
     */
    public static void drawLives(GraphicsContext drawLivesgc) {
        int playerLives = 3; // Number of lives a player starts off with in the game

        Image playerLife1 = new Image("file:frog.png", 100,  75, false, false); // Variable to contain image of player life
        Image playerLife2 = new Image("file:frog.png", 100, 75, false, false); // Variable to contain image of player life
        Image playerLife3 = new Image("file:frog.png", 110, 75, false,false); // Variable to contain image of player life

        // Cases to determine how many lives a player has left in the game
        switch (playerLives) {
            case 3: // Player has 3 lives
                drawLivesgc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                drawLivesgc.drawImage(playerLife2, 110, 5); // Coordinates on canvas to draw player life
                drawLivesgc.drawImage(playerLife3, 210, 5); // Coordinates on canvas to draw player life
                break;
            case 2: // Player has 2 lives
                drawLivesgc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                drawLivesgc.drawImage(playerLife2, 110, 5); // Coordinates on canvas to draw player life
                break;
            case 1: // Player has 1 life
                drawLivesgc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                break;
            default: // Player has 0 lives
                break;
        }


    }


    public static int score; // Variable to keep track of the player's score
    private int negativeScore = -50; // Integer value that deducts from the player's score if they hit a negative perk
    private int positiveScore = 100; // Integer value that adds to the player's score if they hit a positive perk
}
