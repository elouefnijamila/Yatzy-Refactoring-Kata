import org.junit.*;

import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void should_scores_sum_of_all_dices_when_placed_on_chance() {
        assertScore(15, Category.CHANCE, 2, 3, 4, 5, 1);
        assertScore(16, Category.CHANCE, 3, 3, 4, 5, 1);
    }

    @Test
    public void should_same_numbers_scores_fifty_when_placed_on_yatzy() {
        assertScore(Yatzy.YATZY_SCORE, Category.YATZY, 4, 4, 4, 4, 4);
        assertScore(Yatzy.YATZY_SCORE, Category.YATZY, 6, 6, 6, 6, 6);
    }

    @Test
    public void should_different_numbers_scores_zero_when_placed_on_yatzy() {
        assertScore(Yatzy.ZERO_SCORE, Category.YATZY, 6, 6, 6, 6, 3);
    }

    @Test
    public void should_scores_sum_of_ones_when_placed_on_ones() {
        assertScore(1, Category.ONES, 1, 2, 3, 4, 5);
        assertScore(2, Category.ONES, 1, 2, 1, 4, 5);
        assertScore(Yatzy.ZERO_SCORE, Category.ONES, 6, 2, 2, 4, 5);
        assertScore(4, Category.ONES, 1, 2, 1, 1, 1);
    }

    @Test
    public void should_scores_sum_of_twos_when_placed_on_twos() {
        assertScore(4, Category.TWOS, 1, 2, 3, 2, 6);
        assertScore(10, Category.TWOS, 2, 2, 2, 2, 2);
    }

    @Test
    public void should_scores_sum_of_threes_when_placed_on_threes() {
        assertScore(6, Category.THREES, 1, 2, 3, 2, 3);
        assertScore(12, Category.THREES, 2, 3, 3, 3, 3);
    }

    @Test
    public void should_scores_sum_of_fours_when_placed_on_fours() {
        assertScore(12, Category.FOURS, 4, 4, 4, 5, 5);
        assertScore(8, Category.FOURS, 4, 4, 5, 5, 5);
        assertScore(4, Category.FOURS, 4, 5, 5, 5, 5);
    }

    @Test
    public void should_scores_sum_of_fives_when_placed_on_fives() {
        assertScore(10, Category.FIVES, 4, 4, 4, 5, 5);
        assertScore(15, Category.FIVES, 4, 4, 5, 5, 5);
        assertScore(20, Category.FIVES, 4, 5, 5, 5, 5);
    }

    @Test
    public void should_scores_sum_of_sixes_when_placed_on_sixes() {
        assertScore(Yatzy.ZERO_SCORE, Category.SIXES, 4, 4, 4, 5, 5);
        assertScore(6, Category.SIXES, 4, 4, 6, 5, 5);
        assertScore(18, Category.SIXES, 6, 5, 6, 6, 5);
    }

    @Test
    public void should_scores_sum_of_the_two_highest_matching_dice_when_placed_on_pair() {
        assertScore(6, Category.PAIR, 3, 4, 3, 5, 6);
        assertScore(10, Category.PAIR, 5, 3, 3, 3, 5);
        assertScore(12, Category.PAIR, 5, 3, 6, 6, 5);
    }

    @Test
    public void should_scores_sum_of_the_two_pairs_of_dices_when_placed_on_two_pair() {
        assertScore(16, Category.TWO_PAIRS, 3, 3, 5, 4, 5);
        assertScore(16, Category.TWO_PAIRS, 3, 3, 5, 5, 5);
    }

    @Test
    public void should_scores_sum_of_three_dices_with_same_number_when_placed_on_three_of_a_kind() {
        assertScore(9, Category.THREE_OF_A_KIND, 3, 3, 3, 4, 5);
        assertScore(15, Category.THREE_OF_A_KIND, 5, 3, 5, 4, 5);
        assertScore(9, Category.THREE_OF_A_KIND, 3, 3, 3, 3, 5);
    }

    @Test
    public void should_scores_sum_of_four_dices_with_same_number_when_placed_on_four_of_a_kind() {
        assertScore(12, Category.FOUR_OF_A_KIND, 3, 3, 3, 3, 5);
        assertScore(20, Category.FOUR_OF_A_KIND, 5, 5, 5, 4, 5);
        assertScore(12, Category.FOUR_OF_A_KIND, 3, 3, 3, 3, 3);
    }

    @Test
    public void should_small_straight_roll_scores_sum_of_all_dices_when_placed_on_small_straight() {
        assertScore(15, Category.SMALL_STRAIGHT, 1, 2, 3, 4, 5);
        assertScore(15, Category.SMALL_STRAIGHT, 2, 3, 4, 5, 1);
        assertScore(Yatzy.ZERO_SCORE, Category.SMALL_STRAIGHT, 1, 2, 2, 4, 5);
    }

    @Test
    public void should_large_straight_roll_scores_sum_of_all_dices_when_placed_on_large_straight() {
        assertScore(20, Category.LARGE_STRAIGHT, 6, 2, 3, 4, 5);
        assertScore(20, Category.LARGE_STRAIGHT, 2, 3, 4, 5, 6);
        assertScore(Yatzy.ZERO_SCORE, Category.LARGE_STRAIGHT, 1, 2, 2, 4, 5);
    }

    @Test
    public void should_full_house_roll_scores_sum_of_all_dices_when_placed_on_full_house() {
        assertScore(18, Category.FULL_HOUSE, 6, 2, 2, 2, 6);
        assertScore(Yatzy.ZERO_SCORE, Category.FULL_HOUSE, 2, 3, 4, 5, 6);
    }

    @Test
    public void should_throw_exception_when_numbers_of_dices_is_not_correct() {
        assertThrows(IllegalArgumentException.class, () -> Yatzy.of(2, 3, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> Yatzy.of(1, 2, 3, 4, 5, 6));
    }

    private void assertScore(int expectedScore, Category actualCategory, int... actualRoll) {
        assertEquals(expectedScore, Yatzy.of(actualRoll).score(actualCategory));
    }
}
