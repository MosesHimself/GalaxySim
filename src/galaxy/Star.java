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
public class Star extends HeavenlyBody  {

    public Star(double x, double y, double radius, Color color)  {
        super(x, y, radius, color);
        this.bodyRadius = 40;
        body.setRadius(bodyRadius);
        type = "Star";
        name = "Star";
        //body.setEffect(new Glow(0.8));
        temp = 999999;
    }
    
    
}
