/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package froggerrenewed;

import java.io.File;
import static java.lang.System.gc;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.image.Image;
import static javafx.scene.input.DataFormat.URL;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Kody
 */
public class FroggerRenewed extends Application {
    private int startTimeSec = 60; // Variable set to 60 s
    private Timeline timeline = new Timeline();
    private boolean isRunning; // Variable to check if the timer is running
    private Label timerText; // Label to contain the timer value
    
    public int score; // Variable to keep track of the player's score
    private int negativeScore = -50; // Integer value that deducts from the player's score if they hit a negative perk
    private int positiveScore = 100; // Integer value that adds to the player's score if they hit a positive perk
    
    @Override
    public void start(Stage theStage) {
        theStage.setTitle("");
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
        // Key event handler for when 'w', 'a', 's', or 'd' is pressed
        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A: // Check to see if the key 'a' is pressed
                        playJumpSound("A"); // Play jump.mp3 whenever 'a' is pressed
                        break;
                    case D: // Check to see if the key 'd' is pressed
                        playJumpSound("D"); // Play jump.mp3 whenever 'd' is pressed
                        break;
                    case W: // Check to see if the key 'w' is pressed
                        playJumpSound("W"); // Play jump.mp3 whenever 'w' is pressed
                        break;
                    case S: // Check to see if the key 's' is pressed
                        playJumpSound("S"); // Play jump.mp3 whenever 's' is pressed
                        break;
                    default:
                        break;
                }
            }
        });
   
        Canvas canvas = new Canvas(1275, 1275);
        root.getChildren().add(canvas);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Test for score
        positivePerk();
        positivePerk();
        negativePerk();
        positivePerk();
        positivePerk();
        negativePerk();
        positivePerk();
        positivePerk();
        negativePerk();
        positivePerk();
        positivePerk();
        negativePerk();
        
        gc.setFill( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font("Times New Roman", FontWeight.BOLD, 20 );
        gc.setFont(theFont);
        
        gc.fillText("Score: " + score, 550, 25); // Coordinates to draw the player's score on the canvas
        gc.fillText("Time: " + startTimeSec, 1150, 25); // Coordinates to draw the time left on the canvas
     
        // Test for player lives
        drawLives(gc);
        
        // Test for audio
        //menuMusic();
        //winSound();
        //loseSound();
        //collisionSound();
        
        theStage.show();
    }
    /**
      Draws the player life count.
      @param gc is the name of the GraphicsContext canvas variable.
    */
    public void drawLives(GraphicsContext gc) {
        int playerLives = 3; // Number of lives a player starts off with in the game
        
        Image playerLife1 = new Image("file:frog.png"); // Variable to contain image of player life
        Image playerLife2 = new Image("file:frog.png"); // Variable to contain image of player life
        Image playerLife3 = new Image("file:frog.png"); // Variable to contain image of player life
        
        // Cases to determine how many lives a player has left in the game
        switch (playerLives) {
            case 3: // Player has 3 lives
                gc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                gc.drawImage(playerLife2, 60, 5); // Coordinates on canvas to draw player life
                gc.drawImage(playerLife3, 110, 5); // Coordinates on canvas to draw player life
                break;
            case 2: // Player has 2 lives
                gc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                gc.drawImage(playerLife2, 60, 5); // Coordinates on canvas to draw player life
                break;
            case 1: // Player has 1 life
                gc.drawImage(playerLife1, 10, 5); // Coordinates on canvas to draw player life
                break;
            default: // Player has 0 lives
                break;
        }
    }
  /**
    Sets up the information for the timer and starts the timer. 
  */ 
  public void startTime() {



      /*if(!(startTimeSec < 0))*/ if(timeline != null) {
          timeline.stop();
      }
         KeyFrame keyframe = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

             @Override
             public void handle(ActionEvent event) {

                 startTimeSec--; // Decrement the starting time


                 boolean isSecondsZero = startTimeSec == 0;
                 boolean timeToChangeBackground = startTimeSec == 0;
                 
                 if(isSecondsZero) {
                     startTimeSec = 60; // Starting value for the timer
                 }
                 
                 if(timeToChangeBackground) {
                     timeline.stop();
                     startTimeSec = 0;
                 }
                 timerText.setText(String.format("%d sec", startTimeSec));
             }
         });
         startTimeSec = 60;
         timeline.setCycleCount(Timeline.INDEFINITE); 
         timeline.getKeyFrames().add(keyframe);
         timeline.playFromStart(); 
         isRunning = true;
      }



  /**
    Stops/pauses the timer for the level.
  */
   public void stopTime() {
       timeline.pause();
   }
 
   /**
     Stops the timer and resets the it to 60 seconds.
   */
   public void resetTime() {
       timeline.stop(); // Stops the timer even if it isn't counting down
       startTimeSec = 60; // Sets the value back to 60 seconds
   }
   
   /**
     Sets up cases to keep up with when a specific key is pressed.
     @param s is a string variable that contains the cases "A", "D", "W", and "S".
   */
   private void playJumpSound(String s) {
        URL path;
        AudioClip ac;
    
        switch(s) {
            case "A":
            case "D":
            case "W":
            case "S":
                path = getClass().getResource("jump.mp3");
                ac = new AudioClip(path.toString());
                ac.play();
                break;
            default:
        }
    }
    
   /**
     Contains the information for the menu.mp3 sound to play while the player is in the menu.
   */
    private void menuMusic() {
        final URL resource = getClass().getResource("menu.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    /**
      Contains the information for the win.mp3 sound to play after a player wins a level.
    */
    private void winSound() {
        final URL resource = getClass().getResource("win.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    /**
      Contains the information for the lose.mp3 sound to play when a player loses a level.
    */
    private void loseSound() {
        final URL resource = getClass().getResource("lose.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    /**
      Contains the information for the collision.mp3 sound to play when a player collides with an AI object.
    */
    private void collisionSound() {
        final URL resource = getClass().getResource("collision.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    
    /**
      Points added to a player's score when they collect a positive perk.
    */
    private void positivePerk() {
        score = score + positiveScore;
    }

    /**
      Points subtracted from a player's score when they collect a negative perk.
    */

    private void negativePerk() {
        score = score + negativeScore;
    }

    /**
       @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
    
}
