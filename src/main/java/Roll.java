import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Roll(List<Integer> dices) {
    static final int NUMBER_OF_DICES = 5;
    static final List<Integer> SMALL_STRAIGHT_ROLL = IntStream.rangeClosed(1, 5).boxed().toList();
    static final List<Integer> LARGE_STRAIGHT_ROLL = IntStream.rangeClosed(2, 6).boxed().toList();

    public Roll(int[] dices) {
        this(Arrays.stream(dices).boxed().toList());
    }

    Stream<Integer> stream() {
        return dices.stream();
    }

    int sum() {
        return stream().mapToInt(Integer::intValue).sum();
    }

    boolean contains(Integer value) {
        return dices().contains(value);
    }

    Map<Integer, Long> counts() {
        return stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
    }

    Optional<Map.Entry<Integer, Long>> highestPair() {
        return filterDicesByCount(2).max(Comparator.comparingInt(Map.Entry::getKey));
    }

    int sumDicesIfCounts(int count) {
        return filterDicesByCount(count).mapToInt(c -> c.getKey() * count).sum();
    }

    int sumOccurencesOf(int value) {
        return filterDicesByValue(value).reduce(0, Integer::sum);
    }

    boolean isYatzy() {
        return counts().values().stream().anyMatch(count -> count == Roll.NUMBER_OF_DICES);
    }

    boolean isFullHouse() {
        return stream().distinct().count() == 2;
    }

    private Stream<Integer> filterDicesByValue(int value) {
        return stream().filter(dice -> dice == value);
    }

    private Stream<Map.Entry<Integer, Long>> filterDicesByCount(int count) {
        return counts().entrySet().stream()
            .filter(diceCount -> diceCount.getValue() >= count);
    }
}
