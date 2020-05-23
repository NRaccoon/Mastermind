package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;


import java.time.Instant;
import java.util.HashMap;


enum Color {
    RED,
    WHITE,
    BLUE,
    PINK,
    ORANGE,
    PURPLE,
    YELLOW,
    GREEN,
}

@Slf4j
public class GameController {

    private String userName;
    private Instant beginGame;
    private HashMap<Color, Image> colors;
    private int lastStep;
    private int rowHelper;


    @FXML
    private Label usernameLabel;

    @FXML
    private GridPane leftPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private Label errorLabel;


    public GameController() {
        colors = new HashMap<Color, Image>();

        colors.put(Color.RED, new Image(getClass().getResource("/images/red.png").toExternalForm()));
        colors.put(Color.WHITE, new Image(getClass().getResource("/images/white.png").toExternalForm()));
        colors.put(Color.BLUE, new Image(getClass().getResource("/images/blue.png").toExternalForm()));
        colors.put(Color.PINK, new Image(getClass().getResource("/images/pink.png").toExternalForm()));
        colors.put(Color.ORANGE, new Image(getClass().getResource("/images/orange.png").toExternalForm()));
        colors.put(Color.PURPLE, new Image(getClass().getResource("/images/purple.png").toExternalForm()));
        colors.put(Color.YELLOW, new Image(getClass().getResource("/images/yellow.png").toExternalForm()));
        colors.put(Color.GREEN, new Image(getClass().getResource("/images/green.png").toExternalForm()));
    }

    @FXML
    public void processColor(ActionEvent event) {
        String colorPressed = ((Button) event.getSource()).getText();
        errorLabel.setText("");
        if(rowHelper != 4) {
            ImageView view = (ImageView) leftPane.getChildren().get(lastStep);
            switch (colorPressed) {
                case "RED":
                    view.setImage(colors.get(Color.RED));
                    break;
                case "WHITE":
                    view.setImage(colors.get(Color.WHITE));
                    break;
                case "BLUE":
                    view.setImage(colors.get(Color.BLUE));
                    break;
                case "PINK":
                    view.setImage(colors.get(Color.PINK));
                    break;
                case "ORANGE":
                    view.setImage(colors.get(Color.ORANGE));
                    break;
                case "PURPLE":
                    view.setImage(colors.get(Color.PURPLE));
                    break;
                case "YELLOW":
                    view.setImage(colors.get(Color.YELLOW));
                    break;
                case "GREEN":
                    view.setImage(colors.get(Color.GREEN));
                    break;
            }
            lastStep++;
            rowHelper++;
        }else{
            errorLabel.setText("You need to check that you guessed correctly or not!");
        }
    }

   /* public void initdata(String userName) {
        this.userName = userName;
        usernameLabel.setText("Current user: " + this.userName);
    }*/

    public void processSubmit(ActionEvent event) {
        if(lastStep%4==0 && lastStep!=0){
            System.out.println("check");
            rowHelper = 0;
        }
        else{
            errorLabel.setText("You must select 4 colors to check!");
        }
    }

    public void processBack(ActionEvent event) {
        if (lastStep > 0) {
            ImageView view = (ImageView) leftPane.getChildren().get(lastStep - 1);
            view.setImage(null);
            lastStep--;
            rowHelper--;
        }
    }
}
