/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class Timer {
    
    public int t;
    
    public String time = "yeet";
    public Label label;
    
    private static int secondsElapsed = 0; 
    private static int minutesElapsed = 0;
    
    //values for how many ticks occur every second/minute
    private final double tickPerSecond = 0.05;
    private final double tickPerMinute = tickPerSecond / 60;
    
    //dont know why netbeans is beefin about my naming convention here
    private static final double anglePerTickSeconds = 6.0;
    
    //timeline and keyframe used by the clocks
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private SolarSystem system;
    
    public Timer(SolarSystem system)  {
        
        this.system = system;
        System.out.println("In the timer");
        boolean running = false;
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }

        keyFrame = new KeyFrame(Duration.millis(tickPerSecond * 1000), (ActionEvent event) -> {
            updateClock();
            //System.out.println("Updating cock");
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
        
        reset();
    
    }
    
    public void setSystem(SolarSystem system)  {
        this.system = system;
    }
    
    public void useRealTime()  {
        
        Date minutes = new Date( );
        SimpleDateFormat min = 
        new SimpleDateFormat ("m");
        
        Date seconds = new Date( );
        SimpleDateFormat sec = 
        new SimpleDateFormat ("s");

        secondsElapsed = Integer.parseInt(sec.format(seconds));
        minutesElapsed = Integer.parseInt(min.format(minutes));
        
        timeline.play();
        
    }
    
    @FXML
    private void updateClock()  {
        t++;
        
        system.update();
        
        secondsElapsed += tickPerSecond;
        minutesElapsed += tickPerMinute;
           
        //reset to 0 after 60
        if(secondsElapsed >= 60)  {
            secondsElapsed = 0;
            minutesElapsed++;
        }    
        if(minutesElapsed >= 60)  {
            minutesElapsed = 0;
        }
        
        //apply rotation to time passed
        double rotationSeconds = secondsElapsed * anglePerTickSeconds;
        double rotationMinutes = minutesElapsed * anglePerTickSeconds;
        
        //update string and label
        time = String.format("%2d:%2d", minutesElapsed, secondsElapsed);
        //label.setText(time);
    }
    
    public void start() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset() {
        System.out.println("made it this far");
        stop();
        t = 0;
        secondsElapsed = 00;
        minutesElapsed = 00;
        
        time = String.format("%2d:%2d", minutesElapsed, secondsElapsed);
        
        //label.setText(time);

    }
    
    
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.RUNNING) {
                return true;
            }
        } 
        return false;
    }
    
    public int getT()  {
        return this.t;
    }
}
