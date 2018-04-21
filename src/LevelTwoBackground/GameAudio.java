package LevelTwoBackground;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class GameAudio {

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


}
