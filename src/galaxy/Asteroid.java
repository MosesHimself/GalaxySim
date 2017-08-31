/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class Asteroid extends HeavenlyBody{
    
    public Asteroid(double x, double y, double radius, Color color,  HeavenlyBody objectOrbiting)  {
        super(x, y, radius, color);
        this.bodyRadius = Math.random() * 5 + 2;
        body.setRadius(bodyRadius);
        body.setFill(color);
        body.setEffect(null);
        body.setStroke(Color.BLACK);
        name = "Asteroid No. " + (int)(Math.random() * 200);
        type = "Asteroid";
        
        
        initOrbit(Math.random() * 500 + 200, objectOrbiting);
        
    }
}
