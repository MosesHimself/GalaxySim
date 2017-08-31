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
public class Moon extends HeavenlyBody {
    
    public Moon(double x, double y, double radius, Color color)  {
        super(x, y, radius, color);
        //body.setEffect(new Glow(0.8));
        
        type = "Moon";
        name = "Moon";
        
    }
}
