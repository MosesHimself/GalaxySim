/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class Planet extends HeavenlyBody  {
    
    
    
    
   
    
    public Planet(double x, double y, double radius, Color color)  {
        
        super(x, y, radius, color);
        this.bodyRadius = Math.random() * 20 + 5;
        body.setRadius(bodyRadius);
        type = "Planet";
        name = "Planet";
        
        
        //body.setEffect(new Glow(0.8));
        int count = (int)(Math.random() * 4);
        for(int i = 0; i < count; i++)  {
            createMoon(i);
        }
        
        
    }
    
    public Planet(double x, double y, double radius, Color color , HeavenlyBody objectOrbiting, int i)  {
        
        super(x, y, radius, color);
        this.bodyRadius = Math.random() * 15 + 5;
        body.setRadius(bodyRadius);
        type = "Planet";
        name = "Planet";
        
        double orbitNum = (i % 11) * 120 + (Math.random() * 100) + 40;
        initOrbit(orbitNum , objectOrbiting);
        
        int count = (int)(Math.random() * 6);
        for(i = 0; i < count; i++)  {
            createMoon(i);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
}
