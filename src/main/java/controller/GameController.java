package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import mastermind.Color;
import mastermind.Mastermind;
import mastermind.PinStruct;
import results.GameResult;
import results.GameResultDao;

import javax.inject.Inject;
import java.io.IOException;


@Slf4j
public class GameController {

    private String userName;

    private int lastStep;
    private int rowHelper;

    @Inject
    private GameResultDao gameResultDao;

    @FXML
    private GridPane leftPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private Label errorLabel;

    @FXML
    private Pane mainPane;

    @FXML
    private Button giveupButton;

    @Inject
    private FXMLLoader fxmlLoader;

    private Mastermind mastermind;

    @FXML
    public void initialize() {
        mainPane.setStyle("-fx-background-color: #A09586");
        mastermind = new Mastermind();
    }

    @FXML
    public void processColor(ActionEvent event) {
        if (rowHelper != 4 && mastermind.getGameState() == 1) {
            String colorPressed = ((Button) event.getSource()).getText();
            errorLabel.setText("");
                ImageView view = (ImageView) leftPane.getChildren().get(lastStep);
                switch (colorPressed) {
                    case "RED":
                        view.setImage(Color.get(Color.RED));
                        break;
                    case "WHITE":
                        view.setImage(Color.get(Color.WHITE));
                        break;
                    case "BLUE":
                        view.setImage(Color.get(Color.BLUE));
                        break;
                    case "PINK":
                        view.setImage(Color.get(Color.PINK));
                        break;
                    case "ORANGE":
                        view.setImage(Color.get(Color.ORANGE));
                        break;
                    case "PURPLE":
                        view.setImage(Color.get(Color.PURPLE));
                        break;
                    case "YELLOW":
                        view.setImage(Color.get(Color.YELLOW));
                        break;
                    case "GREEN":
                        view.setImage(Color.get(Color.GREEN));
                        break;
                }
                lastStep++;
                rowHelper++;
        } else if(mastermind.getGameState() == 1){
            errorLabel.setText("You need to check that you guessed correctly or not!" + "\n" +
                    "Use Submit button!" + "\n" +
                    "Or change your selected colors with Back button!");
        }
    }

    public void processSubmit(ActionEvent event) {
        errorLabel.setText("");
        if (lastStep % 4 == 0 && lastStep != 0 && lastStep != 40) {
            PinStruct pins = mastermind.process(leftPane, lastStep);

            for (int i = 0; i < pins.getBlack() + pins.getWhite(); i++) {
                ImageView rightView = (ImageView) rightPane.getChildren().get(((int) Math.floor(lastStep / 4) - 1) * 4 + i);
                if (i < pins.getBlack()) {
                    rightView.setImage(Color.get(Color.BLACK));
                } else
                    rightView.setImage(Color.get(Color.WHITE));
            }
            rowHelper = 0;

            if(pins.getBlack() + pins.getWhite() == 0){
                errorLabel.setText("All of your guesses are wrong!");
            }else if(pins.getBlack() == 4) {
                mastermind.setGameState(2);
                mastermind.setSolved(true);
                giveupButton.setText("Finish");
                errorLabel.setText("You won the game, click the Finish button!");
            }

        } else if(lastStep < 40 ){
            errorLabel.setText("You must select 4 colors to check!" + "\n" +
                    "Use Color buttons");
        }else{
            mastermind.setGameState(3);
            giveupButton.setText("Finish");
            errorLabel.setText("You lose the game, click the Finish button!");
        }
    }

    public void processBack(ActionEvent event) {
        if (lastStep > 0 && rowHelper > 0 && mastermind.getGameState() == 1) {
            errorLabel.setText("");
            ImageView view = (ImageView) leftPane.getChildren().get(lastStep - 1);
            view.setImage(null);
            lastStep--;
            rowHelper--;
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private GameResult getResult() {
        lastStep = (int) Math.floor(lastStep/4);
        GameResult result = GameResult.builder()
                .player(userName)
                .solved(mastermind.isSolved())
                .steps(lastStep)
                .build();
        return result;
    }

    public void finishGame(ActionEvent actionEvent) throws IOException {
        gameResultDao.persist(getResult());

        if (mastermind.isSolved()) {
            fxmlLoader.setLocation(getClass().getResource("/fxml/finish.fxml"));
        }else{
            fxmlLoader.setLocation(getClass().getResource("/fxml/fail.fxml"));
        }
        Parent root = fxmlLoader.load();
        if (!mastermind.isSolved())
            for (int i = 0; i < 4; i++)
                fxmlLoader.<FinishController>getController().setColor(i, Color.get(Color.getByValue(mastermind.getGuessColors()[i])));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
