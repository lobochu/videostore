import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Created by twlcbo on 2017/2/9.
 */
public class VipTariffTest {

        private VipTariff tariff = new VipTariff();

        @Test
        public void regular_movie_pricing() throws Exception {
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 1)), equalTo(1.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 2)), equalTo(2.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.REGULAR), 3)), equalTo(3.0));
        }

        @Test
        public void childrens_movie_pricing() throws Exception {
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 1)), equalTo(0.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 2)), equalTo(0.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 3)), equalTo(1.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 4)), equalTo(2.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.CHILDRENS), 5)), equalTo(3.0));

        }

        @Test(expected = IllegalArgumentException.class)
        public void finding_out_what_happens() throws Exception {
                new StandardTariff().calculatePrice(new Rental(new Movie("title", 999), 1));
        }

        @Test
        public void new_release_pricing() throws Exception {
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 1)), equalTo(2.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 2)), equalTo(4.0));
                assertThat(tariff.calculatePrice(new Rental(new Movie("title", Movie.NEW_RELEASE), 3)), equalTo(6.0));
        }
}