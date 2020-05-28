package mastermind;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    RED,
    WHITE,
    BLUE,
    PINK,
    ORANGE,
    PURPLE,
    YELLOW,
    GREEN,
    BLACK;

    private static HashMap<Color, Image> colors = new HashMap<Color, Image>();

    public static void initColors(){
        if (!colors.isEmpty())
            return;
        colors.put(Color.RED, new Image(Color.class.getResource("/images/red.png").toExternalForm()));
        colors.put(Color.WHITE, new Image(Color.class.getResource("/images/white.png").toExternalForm()));
        colors.put(Color.BLUE, new Image(Color.class.getResource("/images/blue.png").toExternalForm()));
        colors.put(Color.PINK, new Image(Color.class.getResource("/images/pink.png").toExternalForm()));
        colors.put(Color.ORANGE, new Image(Color.class.getResource("/images/orange.png").toExternalForm()));
        colors.put(Color.PURPLE, new Image(Color.class.getResource("/images/purple.png").toExternalForm()));
        colors.put(Color.YELLOW, new Image(Color.class.getResource("/images/yellow.png").toExternalForm()));
        colors.put(Color.GREEN, new Image(Color.class.getResource("/images/green.png").toExternalForm()));
        colors.put(Color.BLACK, new Image(Color.class.getResource("/images/black.png").toExternalForm()));
    }

    public static Color getByValue(int value) {
        for(Color c : Color.values())
            if (value == c.ordinal())
                return c;
        return null;
    }

    public static Color findByImage(Image image) {
        initColors();

        for (Map.Entry<Color, Image> e : colors.entrySet())
            if (e.getValue().equals(image))
                return e.getKey();
        return null;
    }

    public static Image get(Color c) {
        initColors();
        return colors.get(c);
    }
}
