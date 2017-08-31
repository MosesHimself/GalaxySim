/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package galaxy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Space_Craft_Trajectories_042
 */
public class GalaxyFXMLController implements Initializable {

    @FXML
    public AnchorPane pane;
    @FXML
    public Label nameLabel;
    @FXML
    public Label typeLabel;
    @FXML
    public Label massLabel;
    @FXML
    public Label radiusLabel;
    @FXML
    public Label orbitTimeLabel;
    @FXML
    public Label tempLabel;
   
    @FXML
    public TextArea ta;
    
    @FXML
    public AnchorPane infoPane;
    
    @FXML
    public ImageView background;
    @FXML
    public MenuBar menuBar;
    
    public Rectangle clip;

    double height;
    double width;
    
    Timer timer;
    
    SolarSystem system;
    int numPlanets;
    
    private Scene scene;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        pane.setOnMousePressed((MouseEvent me) -> {
            System.out.println("Mouse entered");
            //selected = true;
        });
        */
    }    
    
    public void ready(Scene scene)  {
        this.scene = scene;
        clip = new Rectangle();
        numPlanets = 10;
        loadSystem();
        
        timer = new Timer(system);
        timer.start();
        
        //
        //ta.setStyle( "-fx-background-color: yellow" );
        
        ChangeListener<Number> changeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            update();
        };
        this.scene.widthProperty().addListener(changeListener);
        this.scene.heightProperty().addListener(changeListener);
        
        
        
    }
    
    public void loadSystem()  {
        if(system == null)  {
            system = new SolarSystem(numPlanets);
            update();

            for(int i = system.numPlanets - 1; i >= 0; i--) {
                //System.out.println("Ayy");
                HeavenlyBody body = system.getBodies().get(i);
                
                
                if (body.isOrbit) {
                    pane.getChildren().add(body.getOrbit());
                    body.getOrbit().setOnMousePressed((MouseEvent me) -> {
                        clearInfo();
                    });
                }
                if(body.moons != null)
                    for (int j = body.numberOfMoons - 1; j >= 0; j--) {
                        Moon moon = body.moons.get(j);
                        pane.getChildren().add(moon.getOrbit());
                        pane.getChildren().add(moon.getBody());
                        moon.getBody().setOnMousePressed((MouseEvent me) -> {
                            //System.out.println("Circle clicked entered");
                            loadInfo(moon);
                        });
                    }

                pane.getChildren().add(body.getBody());
                body.getBody().setOnMousePressed((MouseEvent me) -> {
                    //System.out.println("Circle clicked entered");
                    loadInfo(body);
                });
            }
            for(HeavenlyBody asteroid: system.asteroids)  {
                pane.getChildren().add(asteroid.getBody());
                asteroid.getBody().setOnMousePressed((MouseEvent me) -> {
                    //System.out.println("Circle clicked entered");
                    loadInfo(asteroid);
                });
            }
            
            pane.getChildren().add(system.star.getBody());
            system.star.getBody().setOnMousePressed((MouseEvent me) -> {
                //System.out.println("Circle clicked entered");
                loadInfo(system.star);
            });
        }
    }
    
    public void clearSystem()  {
        if(system != null)  {
            for (HeavenlyBody body : system.getBodies()) {
                //System.out.println("Ayy");

                if (body.isOrbit) {
                    pane.getChildren().remove(body.getOrbit());
                }
                pane.getChildren().remove(body.getBody());
                if(body.numberOfMoons != 0)
                for(Moon moon: body.moons)  {
                    pane.getChildren().remove(moon.getOrbit());
                    pane.getChildren().remove(moon.getBody());
                }
            }
            pane.getChildren().remove(system.star.getBody());
            for(HeavenlyBody asteroid: system.asteroids)  {
                pane.getChildren().remove(asteroid.getBody());
            }
            system = null;
        }
    }
    
    public void update()  {
        height = scene.getHeight() - menuBar.getHeight();
        width = scene.getWidth();
        infoPane.setLayoutX(width - infoPane.getWidth());
        infoPane.setLayoutY((height/2) - (infoPane.getHeight()/2) );
        //System.out.println("The height and width of the background are" + background.getX() + " and " + background.getY());
        system.changeSize(height, width);
        clip.setHeight(height);
        clip.setWidth(width);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        pane.setClip(clip);
    }
    
    public void clearInfo()  {
        infoPane.setVisible(false);
        ta.clear();
        //typeLabel.setText("Type: " + body.type);
    }
    
    public void loadInfo(HeavenlyBody body)  {
        //infoPane.toFront();
        ta.clear();
        infoPane.setVisible(true);
        ta.appendText("Name: " + body.name);
        ta.appendText("\nMass: " + (int)(body.bodyRadius * 2013));
        ta.appendText("\nType: " + body.type);
        ta.appendText("\nRadius: " + (int)(body.bodyRadius * 1000));
        ta.appendText("\nOrbit: " + (int)(body.speed * 1000));
        ta.appendText("\nTemperature: " + (int)(body.speed * 3001));
        //typeLabel.setText("Type: " + body.type);
    }
    
    public void end()  {
        timer.stop();
    }
    
    @FXML
    private void handleColorChange(ActionEvent event)  {
        
        /*
        MenuItem menuItem = (MenuItem)(event.getSource());
        switch(menuItem.getId())  {
            case "Blue":
                lightColor = Color.SKYBLUE;
                darkColor = Color.DARKBLUE;
                break;
            
            case "Verde":
                lightColor = Color.WHITE;
                darkColor = Color.GREEN;
                break;
                    
            case "PurpleRain":
                lightColor = Color.PURPLE;
                darkColor = Color.BLACK;
                break;
                
            case "Default":
                lightColor = Color.RED;
                darkColor = Color.BLACK;
                break;
                
            case "Wood":
                lightColor = Color.SADDLEBROWN;
                darkColor = Color.KHAKI;
                break;
        }
        update();

    */
    }
    
    @FXML
    public void handleRefresh(ActionEvent event)  {
        System.out.println("Should be refreshing");
        clearInfo();
        clearSystem();
        loadSystem();
        system.update();
        timer.setSystem(system);
        
    }
    
    @FXML
    private void handleAbout(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Solar System Simulator");
        alert.setContentText("This application was developed by HG King for fun at the University of Missouri.");
        alert.showAndWait();
    }
    
    @FXML
    private void handleHelp(ActionEvent event)  {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Solar System Simulator");
        alert.setContentText("This application simulates a solar system using center points, trig functions, and radii to create a simple \nparametric coordinate system to"
                + "make objects orbit eachother.\n\nClick on an object to view its data.");
        alert.showAndWait();
    }
}
