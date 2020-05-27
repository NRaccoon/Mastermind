package mastermind;

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

    public static Color getByValue(int value) {
        for(Color c : Color.values())
            if (value == c.ordinal())
                return c;
        return null;
    }
}
