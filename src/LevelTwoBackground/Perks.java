package LevelTwoBackground;

import javafx.scene.image.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Perks {

    public static Image loadPerk() {

        Image pinkFly = new Image("file:PinkFly.png", 100, 100, false, false);
        Image fly = new Image("file:Fly.png", 100, 100, false, false);
        Image giantLadyBug = new Image("file:GiantLadyBug.png", 100, 100, false, false);
        Image littleBee = new Image("file:LittleBee.png", 100, 100, false, false);
        Image powerDrink = new Image("file:PowerDrinkPowerUP.png", 100, 100, false, false);
        Image protienBar = new Image("file:ProtienBarPowerUp.png", 100, 100, false, false);
        Image candyOne = new Image("file:CandyPowerDown.png", 100, 100, false, false);
        Image candyTwo = new Image("file:CandyPowerDown2.png", 100, 100, false, false);
        Image redBeetle = new Image("file:RedBeetle.png", 100, 100, false, false);
        Image sodaBottle = new Image("file:SodaBottlePowerDown.png", 100, 100, false, false);
        Image sodaCan = new Image("file:SodaPowerDown.png", 100, 100, false, false);
        Image yellowBeetle = new Image("file:YellowBeetle.png", 100, 100, false, false);

        pickPerk randomImage = pickPerk.getRandomPerk();
        final int DELAY = 5000; //5 second delay for timer
        javax.swing.Timer perkTimer = new javax.swing.Timer(DELAY, new ActionListener(){ public void actionPerformed(ActionEvent event)
        {pickPerk.getRandomPerk();}});
        perkTimer.start();



        switch (randomImage.toString()) {
            case "PINKFLY":
                return pinkFly;
            case "FLY":
                return fly;
            case "GIANTLADYBUG":
                return giantLadyBug;
            case "LITTLEBEE":
                return littleBee;
            case "POWERDRINK":
                return powerDrink;
            case "PROTIENBAR":
                return protienBar;
            case "CANDYONE":
                return candyOne;
            case "CANDYTWO":
                return candyTwo;
            case "REDBEETLE":
                return redBeetle;
            case "SODABOTTLE":
                return sodaBottle;
            case "SODACAN":
                return sodaCan;
            case "YELLOWBEETLE":
                return yellowBeetle;
            default:
                break;
        }

        return null;

    }


    private enum pickPerk {
        PINKFLY,
        FLY,
        GIANTLADYBUG,
        LITTLEBEE,
        POWERDRINK,
        PROTIENBAR,
        CANDYONE,
        CANDYTWO,
        REDBEETLE,
        SODABOTTLE,
        SODACAN,
        YELLOWBEETLE;

        public static pickPerk getRandomPerk() {
             Random generator = new Random();
            return values()[generator.nextInt(values().length)];
        }

    }
}

