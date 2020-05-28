package mastermind;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * The color handle class.
 */
public enum Color {
    /**
     * This is contain the RED color enum.
     */
    RED,
    /**
     * This is contain the WHITE color enum.
     */
    WHITE,
    /**
     * This is contain the BLUE color enum.
     */
    BLUE,
    /**
     * This is contain the PINK color enum.
     */
    PINK,
    /**
     * This is contain the ORANGE color enum.
     */
    ORANGE,
    /**
     * This is contain the PURPLE color enum.
     */
    PURPLE,
    /**
     * This is contain the YELLOW color enum.
     */
    YELLOW,
    /**
     * This is contain the GREEN color enum.
     */
    GREEN,
    /**
     * This is contain the BLACK color enum.
     */
    BLACK;

    /**
     * Make color and image key value pair.
     */
    private static HashMap<Color, Image> colors = new HashMap<Color, Image>();

    /**
     * Add images to the colors.
     */
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

    /**
     * Return colors based on values.
     * @param value Value of the color.
     * @return Return a color.
     */
    public static Color getByValue(int value) {
        for(Color c : Color.values())
            if (value == c.ordinal())
                return c;
        return null;
    }

    /**
     * Return colors based on images.
     * @param image Image of the color.
     * @return Return a color.
     */
    public static Color findByImage(Image image) {
        initColors();

        for (Map.Entry<Color, Image> e : colors.entrySet())
            if (e.getValue().equals(image))
                return e.getKey();
        return null;
    }

    /**
     * Return the color.
     * @param c Color name.
     * @return Color.
     */
    public static Image get(Color c) {
        initColors();
        return colors.get(c);
    }
}
