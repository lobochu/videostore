import static java.lang.Math.max;

/**
 * Created by twlcbo on 2017/1/19.
 */
public class Tariff {

        public double calculatePrice(Rental rental) {

                // determines the amount for each line
                final int daysRented = rental.getDaysRented();
                final int priceCode = rental.getMovie().getPriceCode();
                switch (priceCode) {
                        case Movie.REGULAR:
                                return 2.0 + max(0, daysRented - 2) * 1.5;
                        case Movie.NEW_RELEASE:
                                return 3.0 * daysRented;
                        case Movie.CHILDRENS:
                                return 1.5 + max(0, daysRented - 3) * 1.5;
                        default:
                                throw new IllegalArgumentException(
                                    "Unhandled price code" + priceCode);

                }
        }
}
