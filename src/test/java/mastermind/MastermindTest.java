package mastermind;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;
import com.sun.javafx.application.PlatformImpl;

import static org.junit.jupiter.api.Assertions.*;

public class MastermindTest {
    @Test
    void testGameState() {
        Mastermind mastermind = new Mastermind();
        assertEquals(1, mastermind.getGameState());
        mastermind.setGameState(2);
        assertEquals(2, mastermind.getGameState());
    }

    @Test
    void testIsSolved() {
        Mastermind mastermind = new Mastermind();
        assertFalse(mastermind.isSolved());
        mastermind.setSolved(true);
        assertTrue(mastermind.isSolved());
    }

    @Test
    void testGuessedColors() {
        Mastermind mastermind = new Mastermind();
        assertEquals(4, mastermind.getGuessColors().length);
    }

    @Test
    void testProcess() {
        PlatformImpl.startup(() -> {});

        Mastermind mastermind = new Mastermind();

        GridPane leftPane = new GridPane();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 4; j++)
                leftPane.add(new ImageView(), i, j);

        assertEquals(new PinStruct(0, 0), mastermind.process(leftPane, 5));

        ((ImageView)leftPane.getChildren().get(0)).setImage(Color.get(Color.getByValue(mastermind.getGuessColors()[0])));
        assertEquals(new PinStruct(0, 1), mastermind.process(leftPane, 5));

        ((ImageView)leftPane.getChildren().get(1)).setImage(Color.get(Color.getByValue(mastermind.getGuessColors()[2])));
        assertEquals(new PinStruct(1, 1), mastermind.process(leftPane, 5));
    }
}
