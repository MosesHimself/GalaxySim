/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public class SolarSystem {
    
    private ArrayList<HeavenlyBody> bodies;
    private AnchorPane pane;
    private double height, width;
    public Star star;
    public int numPlanets;
    public ArrayList<HeavenlyBody> asteroids;
    
    public SolarSystem(int numPlanets)  {
        
        System.out.println("Made the system");
        this.numPlanets = numPlanets;
        bodies = new ArrayList<>();
        asteroids = new ArrayList<>();
        star = new Star(1.0, 1.0, 20, Color.rgb(254, 230, 74));
       
        for(int i = 0; i < 200; i++)  {
            Asteroid asteroid = new Asteroid(1.0, 1.0, 10, Color.BROWN, star);
            asteroids.add(asteroid);
        }
        
        
        for(int i = 0; i < this.numPlanets; i++)  {
            
            
            Color[] color = {Color.rgb(226, 107, 159), Color.rgb(60, 109, 188), Color.GOLDENROD, Color.rgb(60, 188, 124), Color.ORANGE, Color.rgb(188, 60, 181), Color.LIGHTCORAL};
            //Color color = Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
            
            Planet planet = new Planet(1.0, 1.0, 10, color[i % 7], star, i);
            
            bodies.add(planet);
        }
        
        
    }
    
    
    public void update()  {
        star.setY(height/2);
        star.setX(width/2);
        for (HeavenlyBody body: bodies)  {
            
            body.update();
            
        }
        for (HeavenlyBody body: asteroids)  {
            
            body.update();
            
        }
        
    }
    
    public void changeSize(double height, double width)  {
        this.height = height;
        this.width = width;
    }
    
    public ArrayList<HeavenlyBody> getBodies()  {
        return this.bodies;
    }
}
