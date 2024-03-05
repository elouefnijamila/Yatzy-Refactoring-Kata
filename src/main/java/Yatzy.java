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

    public int chance() {
        return roll.sum();
    }

    public int yatzy() {
        return roll.isYatzy() ? YATZY_SCORE : ZERO_SCORE;
    }

    public int ones() {
        return roll.sumOccurencesOf(1);
    }

    public int twos() {
        return roll.sumOccurencesOf(2);
    }

    public int threes() {
        return roll.sumOccurencesOf(3);
    }

    public int fours() {
        return roll.sumOccurencesOf(4);
    }

    public int fives() {
        return roll.sumOccurencesOf(5);
    }

    public int sixes() {
        return roll.sumOccurencesOf(6);
    }

    public int scorePair() {
        return roll.highestPair().map(value -> value.getKey() * 2).orElseGet(() -> ZERO_SCORE);
    }

    public int twoPairs() {
        return roll.sumDicesIfCounts(2);
    }

    public int threeOfAKind() {
        return roll.sumDicesIfCounts(3);
    }

    public int fourOfAKind() {
        return roll.sumDicesIfCounts(4);
    }

    public int smallStraight() {
        return Roll.SMALL_STRAIGHT_ROLL.stream().filter(element -> !roll.contains(element)).count() == 0 ? roll.sum() : ZERO_SCORE;
    }

    public int largeStraight() {
        return Roll.LARGE_STRAIGHT_ROLL.stream().filter(element -> !roll.contains(element)).count() == 0 ? roll.sum() : ZERO_SCORE;
    }

    public int fullHouse() {
        return roll.isFullHouse() ? roll.sum() : 0;
    }

}