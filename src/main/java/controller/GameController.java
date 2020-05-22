package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    private int stepCount;
    private Instant beginGame;
    private HashMap<Color, Image> colors;
    int i=0;


    @FXML
    private Label usernameLabel;

    @FXML
    private GridPane leftPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private GridPane colorPane;

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
        System.out.println(colorPressed);
                ImageView view = (ImageView) leftPane.getChildren().get(i);
                switch(colorPressed){
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
        i++;
    }

   /* public void initdata(String userName) {
        this.userName = userName;
        usernameLabel.setText("Current user: " + this.userName);
    }*/


    public void clickLeft(MouseEvent mouseEvent) {


        int clickedColumn = leftPane.getColumnIndex((Node)mouseEvent.getSource());
        int clickedRow = leftPane.getRowIndex((Node)mouseEvent.getSource());

        System.out.println("left pane, (" + clickedColumn + "," + clickedRow + ")");

    }

    public void clickRight(MouseEvent mouseEvent) {


        int clickedColumn = rightPane.getColumnIndex((Node)mouseEvent.getSource());
        int clickedRow = rightPane.getRowIndex((Node)mouseEvent.getSource());

        System.out.println("right pane, (" + clickedColumn + "," + clickedRow + ")");
    }
}
