package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.IOException;

@Slf4j
public class LaunchController {

    @FXML
    private TextField usernameTextfield;

    @FXML
    private Label errorLabel;

    @FXML
    private Pane startPane;

    @Inject
    private FXMLLoader fxmlLoader;

    @FXML
    public void initialize() {
        startPane.setStyle("-fx-background-color: #A09586");
    }

    public void startAction(ActionEvent actionEvent) throws IOException {
        if (usernameTextfield.getText().isEmpty()) {
            errorLabel.setText("* Username is empty!");
        } else {
            fxmlLoader.setLocation(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().setUserName(usernameTextfield.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            log.info("Username is set to {}, loading game scene.", usernameTextfield.getText());
        }

    }
}
