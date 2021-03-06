import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Created by twlcbo on 2017/2/9.
 */
public class LoyaltyPlanTest {

        private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();

        @Test
        public void a_rental_earns_one_point() throws Exception {
                assertThat(loyaltyPlan.calculatePoints(new Rental(new Movie("title", Movie.REGULAR), 1)), Matchers.equalTo(1));
        }
        @Test
        public void new_release_movie_for_one_day_earns_one_point() throws Exception {
                assertThat(loyaltyPlan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 1)), Matchers.equalTo(1));
        }
        @Test
        public void new_release_movie_for_more_than_one_day_earns_two_points() throws Exception {
                assertThat(loyaltyPlan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 2)), Matchers.equalTo(2));
                assertThat(loyaltyPlan.calculatePoints(new Rental(new Movie("title", Movie.NEW_RELEASE), 3)), Matchers.equalTo(2));
        }

}