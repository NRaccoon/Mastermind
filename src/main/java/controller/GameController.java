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
import java.util.List;

@Slf4j
public class GameController {

    private String userName;
    private int stepCount;
    private Instant beginGame;


    @FXML
    private Label usernameLabel;

    @FXML
    private GridPane leftPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private GridPane colorPane;

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
    public void clickColor(MouseEvent mouseEvent) {


        int clickedColumn = colorPane.getColumnIndex((Node)mouseEvent.getSource());
        int clickedRow = colorPane.getRowIndex((Node)mouseEvent.getSource());

        System.out.println("color pane, (" + clickedColumn + "," + clickedRow + ")");
    }
}
