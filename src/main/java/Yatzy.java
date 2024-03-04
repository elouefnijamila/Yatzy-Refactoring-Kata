import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy {
    public static final int NUMBER_OF_DICES = 5;
    public static final int ZERO_SCORE = 0;
    public static final int YATZY_SCORE = 50;

    private int[] dices;

    public static Yatzy of(int... dices) {
        if(dices.length != NUMBER_OF_DICES)
            throw new IllegalArgumentException("Dices number is not correct.");

        return new Yatzy(dices);
    }

    private Yatzy(int... dices) {
        this.dices = dices;
    }

    public int chance() {
        return Arrays.stream(dices).sum();
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

    private int sumNumberInRoll(int number, int... roll) {
        return Arrays.stream(roll).filter(dice -> dice == number).sum();
    }

    public int scorePair() {
        return counts().entrySet().stream()
            .filter(c -> c.getValue() >= 2)
            .max(Comparator.comparingInt(Map.Entry::getKey)).map(value -> value.getKey() * 2).orElseGet(() -> 0);
    }

    private Map<Integer, Long> counts() {
        return Arrays.stream(dices).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1)
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[_1-1]++;
        tallies[_2-1]++;
        tallies[d3-1]++;
        tallies[d4-1]++;
        tallies[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i+1) * 4;
        return 0;
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t;
        t = new int[6];
        t[d1-1]++;
        t[d2-1]++;
        t[d3-1]++;
        t[d4-1]++;
        t[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i+1) * 3;
        return 0;
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
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
}



