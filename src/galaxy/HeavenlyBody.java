/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Space_Craft_Trajectories_042
 */
public abstract class HeavenlyBody {
    
    boolean selected;
    
    protected String name;
    protected String type;
    HeavenlyBody objectOrbiting;
    
    public ArrayList<Moon> moons;
    
    protected Circle body;
    
    protected double x, y;
    protected double mass;
    protected double bodyRadius;
    
    protected int temp;
    protected Color color;
    
    double speed;
    double timer;
    
    protected boolean isOrbit;
    protected Circle orbit;
    protected double orbitRadius;
    protected double orbitOriginX, orbitOriginY;
    
    protected int numberOfMoons;
    
    public HeavenlyBody(double x, double y, double radius, Color color)  {
        timer = (Math.random() * 100);
        
        this.bodyRadius = radius;
        this.x = x;
        this.y = y;
        this.color = color;
        
        body = new Circle();
        
        body.setCenterX(x);
        body.setCenterY(y);
        body.setFill(color);
        body.setRadius(bodyRadius);
        
        body.setStroke(Color.BLACK);
        
        body.setOnMouseEntered((MouseEvent me) -> {
            body.setStroke(Color.CHARTREUSE);
        });
        
        body.setOnMouseExited((MouseEvent me) -> {
            body.setStroke(Color.BLACK);
        });
        body.setEffect(new Glow(0.8));
        numberOfMoons = 0;
        //this.mass = (this.bodyRadius * 99);
    }
    
    public void initOrbit(double radius, HeavenlyBody body)  {
        
        objectOrbiting = body;
        
        speed = (2/radius) / (bodyRadius) ;
        this.orbitRadius = radius;
        setOrbitCenter(x, y);
        isOrbit = true;
        orbit = new Circle();
        
        orbit.setOnMouseEntered((MouseEvent me) -> {
            //System.out.println("orbit clicked entered " + this.name);
            orbit.setStroke(Color.PINK);
            this.body.setStroke(Color.PINK);
        });
        
        orbit.setOnMouseExited((MouseEvent me) -> {
            //System.out.println("orbit clicked exited " + this.name);
            orbit.setStroke(Color.rgb(88, 124, 183));
            this.body.setStroke(Color.BLACK);
        });
        
        orbit.setCenterX(x);
        orbit.setCenterY(y);
        orbit.setStroke(Color.rgb(88, 124, 183));
        if(type != "Moon")
            orbit.setStrokeWidth(1.5);
        orbit.setFill(Color.TRANSPARENT);
        orbit.setRadius(orbitRadius);
        
        
        
        //temp = (int)(1 / orbitRadius);
        
        
    }
    
    public void update()  {
        orbitOriginX = objectOrbiting.getX();
        orbitOriginY = objectOrbiting.getY();
        
        this.timer += speed;
        if(isOrbit)  {
            x = (orbitRadius * Math.cos(timer) + (orbitOriginX));
            y = (orbitRadius * Math.sin(timer) + (orbitOriginY));
            body.setCenterX(x);
            body.setCenterY(y);
            orbit.setCenterX(orbitOriginX);
            orbit.setCenterY(orbitOriginY);
        }
        
        if(numberOfMoons != 0)  {
            for(Moon moon: moons)  {
                moon.update();
            }
        }
        
    }
    
    public void createMoon(int i)  {
        if(numberOfMoons == 0)  {
            moons = new ArrayList();
        }
        numberOfMoons++;
        double moonOrbit = (Math.random() * i * 17 + bodyRadius + 3);
        Moon moon = new Moon(0.1, 0.1, 3, Color.ALICEBLUE);
        
        if(isOrbit)
            while(moonOrbit > orbitRadius - objectOrbiting.bodyRadius)  {
                moonOrbit -= 5;
                orbit.setStroke(Color.BLUE);
            }

        
        moon.initOrbit(moonOrbit, this);
        
        moons.add(moon);
    }
    
    public ArrayList<Moon> getMoons()  {
        return this.moons;
    }
    
    public void setName(String name)  {
        this.name = name;
    }
    
    public void setX(double x)  {
        this.x = x;
        body.setCenterX(x);
    }
    
    public void setY(double y)  {
        this.y = y;
        body.setCenterY(y);
    }
    
    public void setTemp(String name)  {
        this.name = name;
    }
    
    public Circle getBody()  {
        return this.body;
    }
    
    public Circle getOrbit()  {
        return this.orbit;
    }
    
    public double getX()  {
        return this.x;
    }
    
    public double getY()  {
        return this.y;
    }
    
    public void setOrbitCenter(double x, double y)  {
        orbitOriginX = x;
        orbitOriginY = y;
    }
    
    public void addMouseClickHandler()  {
        body.setOnMousePressed((MouseEvent me) -> {
            System.out.println("Circle clicked entered");
            selected = true;
        });
    }
}
