package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


import javax.inject.Inject;
import java.io.IOException;

@Slf4j
public class FinishController {
    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    private Pane endPane;

    @FXML
    private Pane finishPane;

    @FXML
    public void initialize() {
        endPane.setStyle("-fx-background-color: #A09586");
    }

    public void setColor(int i, Image color) {
        ((ImageView)finishPane.getChildren().get(i)).setImage(color);
    }

    public void startGame(ActionEvent actionEvent) throws IOException {
        log.info("Loading launch scene.");
        fxmlLoader.setLocation(getClass().getResource("/fxml/launch.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void leaderboardUp(ActionEvent actionEvent) throws IOException {
        fxmlLoader.setLocation(getClass().getResource("/fxml/top.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
