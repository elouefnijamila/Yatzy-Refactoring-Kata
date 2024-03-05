import java.util.*;
import java.util.stream.*;

public class Yatzy {
    public static final int NUMBER_OF_DICES = 5;
    public static final int ZERO_SCORE = 0;
    public static final int YATZY_SCORE = 50;

    private int[] dices;

    public static Yatzy of(int... dices) {
        if (dices.length != NUMBER_OF_DICES)
            throw new IllegalArgumentException("Dices number is not correct.");

        return new Yatzy(dices);
    }

    private Yatzy(int... dices) {
        this.dices = dices;
    }

    public int chance() {
        return sum();
    }

    public int yatzy() {
        return counts().values().stream().anyMatch(count -> count == NUMBER_OF_DICES) ? YATZY_SCORE : ZERO_SCORE;
    }

    public int ones() {
        return sumNumberInRoll(1, dices);
    }

    public int twos() {
        return sumNumberInRoll(2, dices);
    }

    public int threes() {
        return sumNumberInRoll(3, dices);
    }

    public int fours() {
        return sumNumberInRoll(4, dices);
    }

    public int fives() {
        return sumNumberInRoll(5, dices);
    }

    public int sixes() {
        return sumNumberInRoll(6, dices);
    }

    public int scorePair() {
        return highestPair().map(value -> value.getKey() * 2).orElseGet(() -> ZERO_SCORE);
    }

    public int twoPairs() {
        return sumNumberMatchingDices(2);
    }

    public int threeOfAKind() {
        return sumNumberMatchingDices(3);
    }

    public int fourOfAKind() {
        return sumNumberMatchingDices(4);
    }

    public int smallStraight() {
        return Arrays.compare(dicesStream().sorted().toArray(), IntStream.rangeClosed(1, 5).toArray()) == 0 ? sum() : ZERO_SCORE;
    }

    public int largeStraight() {
        return Arrays.compare(dicesStream().sorted().toArray(), IntStream.rangeClosed(2, 6).toArray()) == 0 ? sum() : ZERO_SCORE;
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    private IntStream dicesStream() {
        return Arrays.stream(dices);
    }

    private int sum() {
        return dicesStream().sum();
    }

    private int sumNumberInRoll(int number, int... roll) {
        return dicesStream().filter(dice -> dice == number).sum();
    }

    private Map<Integer, Long> counts() {
        return dicesStream().boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
    }

    private Optional<Map.Entry<Integer, Long>> highestPair() {
        return counts().entrySet().stream()
            .filter(c -> c.getValue() >= 2)
            .max(Comparator.comparingInt(Map.Entry::getKey));
    }

    private int sumNumberMatchingDices(int number) {
        return counts().entrySet().stream().filter(c -> c.getValue() >= number).mapToInt(c -> c.getKey() * number).sum();
    }

}