public class Yatzy {
    public static final int ZERO_SCORE = 0;
    public static final int YATZY_SCORE = 50;

    private Roll roll;

    public static Yatzy of(int... dices) {
        if (dices.length != Roll.NUMBER_OF_DICES)
            throw new IllegalArgumentException("Dices number is not correct.");
        return new Yatzy(dices);
    }

    private Yatzy(int... dices) {
        this.roll = new Roll(dices);
    }

    public int score(Category category){
        return category.score(roll);
    }
}