package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.Ingres9Dialect;


import java.security.KeyStore;
import java.time.Instant;
import java.util.*;

@Slf4j
public class GameController {

    private String userName;
    private Instant beginGame;
    private HashMap<Color, Image> colors;
    private int lastStep;
    private int rowHelper;
    private Random rand;
    private int[] guessColors;

    @FXML
    private Label usernameLabel;

    @FXML
    private GridPane leftPane;

    @FXML
    private GridPane rightPane;

    @FXML
    private Label errorLabel;

    @FXML
    private Pane mainPane;


/** Kép alapján a kulcs(szín) lekérdezése */

    public Color findByImage(Image image) {
        for(Map.Entry<Color, Image> e : colors.entrySet())
            if(e.getValue().equals(image))
                return e.getKey();
        return null;
    }

    @FXML
    public void initialize() {
        mainPane.setStyle("-fx-background-color: #A09586");

        colors = new HashMap<Color, Image>();

        colors.put(Color.RED, new Image(getClass().getResource("/images/red.png").toExternalForm()));
        colors.put(Color.WHITE, new Image(getClass().getResource("/images/white.png").toExternalForm()));
        colors.put(Color.BLUE, new Image(getClass().getResource("/images/blue.png").toExternalForm()));
        colors.put(Color.PINK, new Image(getClass().getResource("/images/pink.png").toExternalForm()));
        colors.put(Color.ORANGE, new Image(getClass().getResource("/images/orange.png").toExternalForm()));
        colors.put(Color.PURPLE, new Image(getClass().getResource("/images/purple.png").toExternalForm()));
        colors.put(Color.YELLOW, new Image(getClass().getResource("/images/yellow.png").toExternalForm()));
        colors.put(Color.GREEN, new Image(getClass().getResource("/images/green.png").toExternalForm()));
        colors.put(Color.BLACK, new Image(getClass().getResource("/images/black.png").toExternalForm()));



        /** Random 4 szín kiválasztása (ismétlés megengedésével) a 8 szín közül*/
        rand = new Random();
        guessColors = new int[4];
        for (int i = 0; i < 4; i++)
            guessColors[i] = rand.nextInt(8);

        /*guessColors[0] = 1;
        guessColors[1] = 1;
        guessColors[2] = 2;
        guessColors[3] = 3;*/

        /*System.out.println(Color.getByValue(guessColors[0]).name()+ "-" + Color.getByValue(guessColors[1]).name() +
                "-" + Color.getByValue(guessColors[2]).name() + "-" + Color.getByValue(guessColors[3]).name());*/

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
            errorLabel.setText("You need to check that you guessed correctly or not!" + "\n" +
                    "Use Submit button!" + "\n" +
                    "Or change your selected colors with Back button!");
        }
    }

   /* public void initdata(String userName) {
        this.userName = userName;
        usernameLabel.setText("Current user: " + this.userName);
    }*/

    public void processSubmit(ActionEvent event) {
        if(lastStep%4==0 && lastStep!=0){
            int blackPin = 0;
            int whitePin = 0;
            List<Integer> whiteExt = new ArrayList<Integer>();
            List<Integer> whiteMyExt = new ArrayList<Integer>();

            for (int i = 0; i < 4; i++) {
                ImageView view = (ImageView) leftPane.getChildren().get(((int)Math.floor(lastStep / 4) - 1) * 4 + i);
                Color color = findByImage(view.getImage());

                if (color.equals(Color.getByValue(guessColors[i]))) {
                    blackPin++;
                    whiteExt.add(i);
                    whiteMyExt.add(i);
                }
            }

            for (int i = 0; i < 4; i++) {
                ImageView view = (ImageView) leftPane.getChildren().get(((int)Math.floor(lastStep / 4) - 1) * 4 + i);
                Color color = findByImage(view.getImage());

                if (!color.equals(Color.getByValue(guessColors[i])) && !whiteMyExt.contains(i)) {
                    for (int j = 0; j < 4; j++) {
                        Color guess = Color.getByValue(guessColors[j]);
                        if (!whiteExt.contains(j) && !whiteMyExt.contains(i)) {
                            if (color.equals(guess)) {
                                whiteExt.add(j);
                                whiteMyExt.add(i);
                                whitePin++;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < blackPin + whitePin; i++) {
                ImageView rightView = (ImageView) rightPane.getChildren().get(((int)Math.floor(lastStep / 4) - 1) * 4 + i);
                if (i < blackPin)
                    rightView.setImage(colors.get(Color.BLACK));
                else
                    rightView.setImage(colors.get(Color.WHITE));
            }
            rowHelper = 0;
        }
        else{
            errorLabel.setText("You must select 4 colors to check!" + "\n" +
                    "Use Color buttons");
        }
    }

    public void processBack(ActionEvent event) {
        errorLabel.setText("");
        if (lastStep > 0 && rowHelper > 0) {
            ImageView view = (ImageView) leftPane.getChildren().get(lastStep - 1);
            view.setImage(null);
            lastStep--;
            rowHelper--;
        }
    }
}
