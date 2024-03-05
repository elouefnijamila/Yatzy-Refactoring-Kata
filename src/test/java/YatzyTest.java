import org.junit.*;

import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void should_scores_sum_of_all_dices_when_placed_on_chance() {
        assertEquals(15, Yatzy.of(2, 3, 4, 5, 1).chance());
        assertEquals(16, Yatzy.of(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void should_same_numbers_scores_fifty_when_placed_on_yatzy() {
        assertEquals(50, Yatzy.of(4, 4, 4, 4, 4).yatzy());
        assertEquals(50, Yatzy.of(6, 6, 6, 6, 6).yatzy());
    }

    @Test
    public void should_different_numbers_scores_zero_when_placed_on_yatzy() {
        assertEquals(0, Yatzy.of(6, 6, 6, 6, 3).yatzy());
    }

    @Test
    public void should_scores_sum_of_ones_when_placed_on_ones() {
        assertEquals(1, Yatzy.of(1, 2, 3, 4, 5).ones());
        assertEquals(2, Yatzy.of(1, 2, 1, 4, 5).ones());
        assertEquals(0, Yatzy.of(6, 2, 2, 4, 5).ones());
        assertEquals(4, Yatzy.of(1, 2, 1, 1, 1).ones());
    }

    @Test
    public void should_scores_sum_of_twos_when_placed_on_twos() {
        assertEquals(4, Yatzy.of(1, 2, 3, 2, 6).twos());
        assertEquals(10, Yatzy.of(2, 2, 2, 2, 2).twos());
    }

    @Test
    public void should_scores_sum_of_threes_when_placed_on_threes() {
        assertEquals(6, Yatzy.of(1, 2, 3, 2, 3).threes());
        assertEquals(12, Yatzy.of(2, 3, 3, 3, 3).threes());
    }

    @Test
    public void should_scores_sum_of_fours_when_placed_on_fours() {
        assertEquals(12, Yatzy.of(4, 4, 4, 5, 5).fours());
        assertEquals(8, Yatzy.of(4, 4, 5, 5, 5).fours());
        assertEquals(4, Yatzy.of(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void should_scores_sum_of_fives_when_placed_on_fives() {
        assertEquals(10, Yatzy.of(4, 4, 4, 5, 5).fives());
        assertEquals(15, Yatzy.of(4, 4, 5, 5, 5).fives());
        assertEquals(20, Yatzy.of(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void should_scores_sum_of_sixes_when_placed_on_sixes() {
        assertEquals(0, Yatzy.of(4, 4, 4, 5, 5).sixes());
        assertEquals(6, Yatzy.of(4, 4, 6, 5, 5).sixes());
        assertEquals(18, Yatzy.of(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void should_scores_sum_of_the_two_highest_matching_dice_when_placed_on_pair() {
        assertEquals(6, Yatzy.of(3, 4, 3, 5, 6).scorePair());
        assertEquals(10, Yatzy.of(5, 3, 3, 3, 5).scorePair());
        assertEquals(12, Yatzy.of(5, 3, 6, 6, 5).scorePair());
    }

    @Test
    public void should_scores_sum_of_the_two_pairs_of_dices_when_placed_on_two_pair() {
        assertEquals(16, Yatzy.of(3, 3, 5, 4, 5).twoPairs());
        assertEquals(16, Yatzy.of(3, 3, 5, 5, 5).twoPairs());
    }

    @Test
    public void should_scores_sum_of_three_dices_with_same_number_when_placed_on_three_of_a_kind() {
        assertEquals(9, Yatzy.of(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15, Yatzy.of(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9, Yatzy.of(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void should_scores_sum_of_four_dices_with_same_number_when_placed_on_four_of_a_kind() {
        assertEquals(12, Yatzy.of(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, Yatzy.of(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(12, Yatzy.of(3, 3, 3, 3, 3).fourOfAKind());
    }

    @Test
    public void should_small_straight_roll_scores_sum_of_all_dices_when_placed_on_small_straight() {
        assertEquals(15, Yatzy.of(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15, Yatzy.of(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0, Yatzy.of(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void should_large_straight_roll_scores_sum_of_all_dices_when_placed_on_large_straight() {
        assertEquals(20, Yatzy.of(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20, Yatzy.of(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0, Yatzy.of(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void should_full_house_roll_scores_sum_of_all_dices_when_placed_on_full_house() {
        assertEquals(18, Yatzy.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy.fullHouse(2, 3, 4, 5, 6));
    }

    @Test
    public void should_throw_exception_when_numbers_of_dices_is_not_correct() {
        assertThrows(IllegalArgumentException.class, () -> Yatzy.of(2, 3, 4, 5));
        assertThrows(IllegalArgumentException.class, () -> Yatzy.of(1, 2, 3, 4, 5, 6));
    }
}
