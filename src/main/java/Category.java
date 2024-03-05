public enum Category implements CategoryStrategy {
    CHANCE(Roll::sum),
    YATZY(roll -> roll.isYatzy() ? Yatzy.YATZY_SCORE : Yatzy.ZERO_SCORE),
    ONES(roll -> roll.sumOccurencesOf(1)),
    TWOS(roll -> roll.sumOccurencesOf(2)),
    THREES(roll -> roll.sumOccurencesOf(3)),
    FOURS(roll -> roll.sumOccurencesOf(4)),
    FIVES(roll -> roll.sumOccurencesOf(5)),
    SIXES(roll -> roll.sumOccurencesOf(6)),
    PAIR(roll -> roll.highestPair().map(value -> value.getKey() * 2).orElse(Yatzy.ZERO_SCORE)),
    TWO_PAIRS(roll -> roll.sumDicesIfCounts(2)),
    THREE_OF_A_KIND(roll -> roll.sumDicesIfCounts(3)),
    FOUR_OF_A_KIND(roll -> roll.sumDicesIfCounts(4)),
    SMALL_STRAIGHT(roll -> Roll.SMALL_STRAIGHT_ROLL.stream().allMatch(roll::contains) ? roll.sum() : Yatzy.ZERO_SCORE),
    LARGE_STRAIGHT(roll -> Roll.LARGE_STRAIGHT_ROLL.stream().allMatch(roll::contains) ? roll.sum() : Yatzy.ZERO_SCORE),
    FULL_HOUSE(roll -> roll.isFullHouse() ? roll.sum() : Yatzy.ZERO_SCORE);

    private final CategoryStrategy categoryStrategy;

    Category(final CategoryStrategy categoryStrategy) {
        this.categoryStrategy = categoryStrategy;
    }

    @Override
    public int score(Roll roll) {
        return this.categoryStrategy.score(roll);
    }
}
